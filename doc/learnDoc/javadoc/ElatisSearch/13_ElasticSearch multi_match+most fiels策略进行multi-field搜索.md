# 13_ElasticSearch multi_match+most fiels���Խ���multi-field����

## ����

* ��best-fields����most-fields����
* best-fields���ԣ���Ҫ��˵��ĳһ��fieldƥ�価���ܶ�Ĺؼ��ʵ�doc���ȷ��ػ���
* most-fields���ԣ���Ҫ��˵�����ܷ��ظ���fieldƥ�䵽ĳ���ؼ��ʵ�doc�����ȷ��ػ���

## ��best_fields������

### 1��best_fields

�ǶԶ��field������������ѡĳ��fieldƥ�����ߵ��Ǹ�������ͬʱ�ڶ��query��߷���ͬ������£���һ���̶��Ͽ�������query�ķ���������˵����Զ��field��������������������ĳһ��field�����ܰ�������ؼ��ֵ�����

* �ŵ㣺ͨ��best_fields���ԣ��Լ��ۺϿ�������field������minimum_should_match֧�֣����Ծ����ܾ�׼�ؽ�ƥ��Ľ�����͵���ǰ��
* ȱ�㣺������Щ��׼ƥ��Ľ������������Ľ��������������̫���ȣ�û��ʲô���ֶ���
* ʵ�ʵ����ӣ��ٶ�֮����������棬��ƥ��ĵ���ǰ�棬���������ľ�ûʲô���ֶ���

### 2��most_fields���ۺ϶��fieldһ����������������ܶ��������field��query���뵽�ܷ����ļ�����������ʱ�ͻ��Ǹ����ӻ⣬��������best_fields�����ʼ���Ǹ�����������һ����׼��ĳһ��document��һ��field��������Ĺؼ��֣�������Ϊ����document�и���fieldƥ�䵽�ˣ�����������ǰ�棻������Ҫ��������sub_title.std������field����������ĳһ��field��׼ƥ��query string�����׸��ߵķ�����������׼ƥ��������ŵ�ǰ��

* �ŵ㣺��������ƥ�����field�Ľ�����͵���ǰ�棬�����������ǱȽϾ��ȵ�
* ȱ�㣺������Щ��׼ƥ��Ľ�����޷����͵���ǰ��
* ʵ�ʵ����ӣ�wiki�����Ե�most_fields���ԣ���������ȽϾ��ȣ����ǵ�ȷҪ���ü�ҳ�����ҵ���ƥ��Ľ��



## ����

�鿴�ִʣ�

```java
POST /forum/_mapping/article
{
  "properties": {
      "sub_title": { 
          "type":     "string",
          "analyzer": "english",
          "fields": {
              "std":   { 
                  "type":     "string",
                  "analyzer": "standard"
              }
          }
      }
  }
}
```



### 1."analyzer": "english"

* Ϊʲô����Ϊ��������õ���������english analyzer���ִַ����Ļ����ͻὫ���ʻ�ԭΪ�����������̬��stemmer
* learning --> learn
* learned --> learn
* courses --> course
* sub_titile: learning coureses --> learn course

### 2."analyzer": "standard"
* ���Ὣ���ʻ�ԭ


## ���� sub_titile �ֶ�

```java
POST /forum/article/_bulk
{ "update": { "_id": "1"} }
{ "doc" : {"sub_title" : "learning more courses"} }
{ "update": { "_id": "2"} }
{ "doc" : {"sub_title" : "learned a lot of course"} }
{ "update": { "_id": "3"} }
{ "doc" : {"sub_title" : "we have a lot of fun"} }
{ "update": { "_id": "4"} }
{ "doc" : {"sub_title" : "both of them are good"} }
{ "update": { "_id": "5"} }
{ "doc" : {"sub_title" : "haha, hello world"} }

```

��ѯ��

```java

GET /forum/article/_search
{
  "query": {
    "match": {
      "sub_title": "learning courses"
    }
  }
}
```

�����

```java
{
  "took": 3,
  "timed_out": false,
  "_shards": {
    "total": 5,
    "successful": 5,
    "failed": 0
  },
  "hits": {
    "total": 2,
    "max_score": 1.219939,
    "hits": [
      {
        "_index": "forum",
        "_type": "article",
        "_id": "2",
        "_score": 1.219939,
        "_source": {
          "articleID": "KDKE-B-9947-#kL5",
          "userID": 1,
          "hidden": false,
          "postDate": "2017-01-02",
          "tag": [
            "java"
          ],
          "tag_cnt": 1,
          "view_cnt": 50,
          "title": "this is java blog",
          "content": "i think java is the best programming language",
          "sub_title": "learned a lot of course"
        }
      },
      {
        "_index": "forum",
        "_type": "article",
        "_id": "1",
        "_score": 0.5063205,
        "_source": {
          "articleID": "XHDK-A-1293-#fJ3",
          "userID": 1,
          "hidden": false,
          "postDate": "2017-01-01",
          "tag": [
            "java",
            "hadoop"
          ],
          "tag_cnt": 2,
          "view_cnt": 30,
          "title": "this is java and elasticsearch blog",
          "content": "i like to write best elasticsearch article",
          "sub_title": "learning more courses"
        }
      }
    ]
  }
}
```

## ����

* sub_title�õ���enligsh analyzer�����Ի�ԭ�˵���
* Ϊʲô����Ϊ��������õ���������english analyzer���ִַ����Ļ����ͻὫ���ʻ�ԭΪ�����������̬��stemmer
* learning --> learn
* learned --> learn
* courses --> course

* sub_titile: learning coureses --> learn course

```java
{ "doc" : {"sub_title" : "learned a lot of course"} }����������{ "doc" : {"sub_title" : "learning more courses"} }��ǰ��
```

ʹ��Ĭ�Ϸִ�����ѯ��enligsh analyzer����
```java

GET /forum/article/_search
{
   "query": {
        "match": {
            "sub_title": "learning courses"
        }
    }
}
```

ͬʱʹ�������ִ�����ѯ
```java
GET /forum/article/_search
{
   "query": {
        "multi_match": {
            "query":  "learning courses",
            "type":   "most_fields", 
            "fields": [ "sub_title", "sub_title.std" ]
        }
    }
}
```

```java
{
  "took": 2,
  "timed_out": false,
  "_shards": {
    "total": 5,
    "successful": 5,
    "failed": 0
  },
  "hits": {
    "total": 2,
    "max_score": 1.219939,
    "hits": [
      {
        "_index": "forum",
        "_type": "article",
        "_id": "2",
        "_score": 1.219939,
        "_source": {
          "articleID": "KDKE-B-9947-#kL5",
          "userID": 1,
          "hidden": false,
          "postDate": "2017-01-02",
          "tag": [
            "java"
          ],
          "tag_cnt": 1,
          "view_cnt": 50,
          "title": "this is java blog",
          "content": "i think java is the best programming language",
          "sub_title": "learned a lot of course"
        }
      },
      {
        "_index": "forum",
        "_type": "article",
        "_id": "1",
        "_score": 1.012641,
        "_source": {
          "articleID": "XHDK-A-1293-#fJ3",
          "userID": 1,
          "hidden": false,
          "postDate": "2017-01-01",
          "tag": [
            "java",
            "hadoop"
          ],
          "tag_cnt": 2,
          "view_cnt": 30,
          "title": "this is java and elasticsearch blog",
          "content": "i like to write best elasticsearch article",
          "sub_title": "learning more courses"
        }
      }
    ]
  }
}
```






