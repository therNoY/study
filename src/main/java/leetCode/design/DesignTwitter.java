package leetCode.design;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，
 * 能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：
 * postTweet(userId, tweetId): 创建一条新的推文
 * getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。
 * 推文必须按照时间顺序由最近的开始排序。
 * follow(followerId, followeeId): 关注一个用户
 * unfollow(followerId, followeeId): 取消关注一个用户
 * 示例:
 * <p>
 * Twitter twitter = new Twitter();
 * // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
 * twitter.postTweet(1, 5);
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * twitter.getNewsFeed(1);
 * <p>
 * // 用户1关注了用户2.
 * twitter.follow(1, 2);
 * <p>
 * // 用户2发送了一个新推文 (推文id = 6).
 * twitter.postTweet(2, 6);
 * <p>
 * // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
 * // 推文id6应当在推文id5之前，因为它是在5之后发送的.
 * twitter.getNewsFeed(1);
 * <p>
 * // 用户1取消关注了用户2.
 * twitter.unfollow(1, 2);
 * <p>
 * // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
 * // 因为用户1已经不再关注用户2.
 * twitter.getNewsFeed(1);
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-twitter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DesignTwitter {

    public static void main(String[] args) {


        System.out.println(new Date());

        System.out.println(String.format("%.2f秒", (float)10000/100.00));
    }

}


class Twitter {

    Map<Integer, Set<Integer>> beFollowMap;
    Map<Integer, Deque<TwitterContent>> newsMap;
    Map<Integer, Deque<TwitterContent>> userNewsMap;

    int twitterTime = 0;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        beFollowMap = new HashMap<>();
        newsMap = new HashMap<>();
        userNewsMap = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        // 1.获取关注我的人
        Set<Integer> followMeList = beFollowMap.get(userId);
        TwitterContent twitterContent = new TwitterContent(userId, tweetId);
        twitterContent.date = twitterTime++;
        if (followMeList != null && followMeList.size() > 0) {
            followMeList.forEach(id -> pubicTweetContent(id, twitterContent));
        }
        // 2.推给自己
        pubicTweetContent(userId, twitterContent);

        // 3.记录自己的最近10条
        Deque<TwitterContent> deque = userNewsMap.get(userId);
        if (deque == null) {
            deque = new LinkedList<>();
            userNewsMap.put(userId, deque);
        }
        if (deque.size() == 10) {
            deque.removeFirst();
        }
        deque.addLast(twitterContent);
    }

    public void pubicTweetContent(Integer id, TwitterContent twitterContent) {
        Deque<TwitterContent> deque = newsMap.get(id);
        if (deque == null) {
            deque = new LinkedList<>();
            newsMap.put(id, deque);
        }
        if (deque.size() == 10) {
            deque.removeFirst();
        }
        deque.addLast(twitterContent);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        Deque<TwitterContent> deque = newsMap.get(userId);
        if (deque != null) {
            Iterator<TwitterContent> contentIterator = deque.descendingIterator();
            while (contentIterator.hasNext()) {
                res.add(contentIterator.next().twitterId);
            }
        }
        return res;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        Set<Integer> followMeList = beFollowMap.get(followeeId);
        if (followMeList == null) {
            followMeList = new HashSet<>();
            beFollowMap.put(followeeId, followMeList);
        }
        followMeList.add(followerId);

        // 判断这个人的最近的推特
        Deque<TwitterContent> contents = userNewsMap.get(followeeId);

        if (contents != null && contents.size() > 0) {
            Deque<TwitterContent> myNews = newsMap.get(followerId);
            if (myNews == null) {
                myNews = new LinkedList<>();
                newsMap.put(followerId, myNews);
            }
            if (myNews.size() > 0) {
                List<TwitterContent> contentList = new ArrayList<>(20);
                contentList.addAll(contents);
                contentList.addAll(myNews);
                myNews.clear();
                if (contentList.size() > 10) {
                    myNews.addAll(contentList.stream()
                            .distinct()
                            .sorted(TwitterContent::compareTo)
                            .limit(10)
                            .collect(Collectors.toList()));
                } else {
                    myNews.addAll(contentList.stream()
                            .distinct()
                            .sorted(TwitterContent::compareTo)
                            .collect(Collectors.toList()));
                }
            } else {
                myNews.addAll(contents);
            }
        }
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        Set<Integer> followMeList = beFollowMap.get(followeeId);
        if (followMeList != null && followMeList.remove(followerId)) {
            // 从这个人的消息列表中移除消息
            Deque<TwitterContent> deque = newsMap.get(followerId);
            if (deque != null && deque.size() > 0) {
                Iterator<TwitterContent> news = deque.iterator();
                while (news.hasNext()) {
                    TwitterContent twitterContent = news.next();
                    if (twitterContent.userId == followeeId) {
                        news.remove();
                    }
                }
            }
        }
    }

    class TwitterContent implements Comparable<TwitterContent> {
        Integer userId;
        Integer twitterId;
        Integer date;

        public TwitterContent(Integer userId, Integer twitterId) {
            this.userId = userId;
            this.twitterId = twitterId;
        }

        @Override
        public String toString() {
            return "TwitterContent{" +
                    "userId=" + userId +
                    ", twitterId=" + twitterId +
                    ", date=" + date +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TwitterContent that = (TwitterContent) o;
            return Objects.equals(userId, that.userId) &&
                    Objects.equals(twitterId, that.twitterId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, twitterId);
        }

        @Override
        public int compareTo(TwitterContent twitterContent) {
            return date.compareTo(twitterContent.date);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */