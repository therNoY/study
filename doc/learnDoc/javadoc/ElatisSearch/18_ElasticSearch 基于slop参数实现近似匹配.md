# 18_ElasticSearch ����slop����ʵ�ֽ���ƥ��


## ����

slop�ĺ���

>* query string�������ı����еļ���term��Ҫ���������ƶ�������һ��documentƥ�䣬����ƶ��Ĵ���������slop

ʵ�ʾ���

һ��query string���������ƶ�֮�����ƥ�䵽һ��document��Ȼ������slop

```java
hello world, java is very good, spark is also very good.
```
* ����doc ʹ�� java spark ��������ʽ match phrase���޷������� ��Ϊ java�� spark �м仹�м����������

## ����һ slop ��ѯ

* slop��phrase match������proximity match������ƥ��
* �������ָ����slop����ô������java spark�����ƶ�����������doc����ƥ��
* ava spark��������һ���ľ��룬���ǿ���Խ����Խ������������proximity match

```java
GET /forum/article/_search
{
    "query": {
        "match_phrase": {
            "title": {
                "query": "java spark",
                "slop":  1
            }
        }
    }
}

```

ԭ��������ƶ�����

* ��spark ��ǰ�ƶ���3��ƥ����doc
* �����slop������3����Ϊjava spark������spark�ƶ���3�Σ��Ϳ��Ը�һ��docƥ������
* slop�ĺ��壬��������˵һ��query string terms�ƶ����Σ���һ��docƥ���ϡ�һ��query string terms���������ƶ�����ȥ���Ը�һ��docƥ����

```java

java		is		very		good		spark		is

java		spark
java		-->		spark
java				-->			spark
java							-->			spark
```

* slop ��ѯ �Ϳ��԰Ѹղ��Ǹ�docƥ���ϣ��Ǹ�doc����Ϊ�������
* �������slop���õ���2����ôjava spark��spark���ֻ���ƶ�2�Σ���ʱ��doc��ƥ�䲻�ϵģ��Ǹ�doc�ǲ�����Ϊ������ص�

```java
GET /forum/article/_search
{
    "query": {
        "match_phrase": {
            "title": {
                "query": "java spark",
                "slop":  3
            }
        }
    }
}
```

## ���Ӷ�


```java
spark		is				best		big			data
```

```java
data		spark
-->			data/spark
spark		<--data
spark		-->				data
spark						-->			data
spark									-->			data
```

* �ƶ���5�β�������

```java

GET /forum/article/_search
{
  "query": {
    "match_phrase": {
      "content": {
        "query": "data spark",
        "slop": 5
      }
    }
  }
}

```


## ������

slop�����£��ؼ������Խ����relevance score�ͻ�Խ��


```java
GET /forum/article/_search
{
  "query": {
    "match_phrase": {
      "content": {
        "query": "java best",
        "slop": 15
      }
    }
  }
}
```

���ؽ��:

* 1��java spark��������һ���ľ��룬���ǿ���Խ������Խ�ߣ�Խ������������proximity match

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
    "max_score": 0.65380025,
    "hits": [
      {
        "_index": "forum",
        "_type": "article",
        "_id": "2",
        "_score": 0.65380025,
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
          "sub_title": "learned a lot of course",
          "author_first_name": "Smith",
          "author_last_name": "Williams",
          "new_author_last_name": "Williams",
          "new_author_first_name": "Smith"
        }
      },
      {
        "_index": "forum",
        "_type": "article",
        "_id": "5",
        "_score": 0.07111243,
        "_source": {
          "articleID": "DHJK-B-1395-#Ky5",
          "userID": 3,
          "hidden": false,
          "postDate": "2017-03-01",
          "tag": [
            "elasticsearch"
          ],
          "tag_cnt": 1,
          "view_cnt": 10,
          "title": "this is spark blog",
          "content": "spark is best big data solution based on scala ,an programming language similar to java spark",
          "sub_title": "haha, hello world",
          "author_first_name": "Tonny",
          "author_last_name": "Peter Smith",
          "new_author_last_name": "Peter Smith",
          "new_author_first_name": "Tonny"
        }
      }
    ]
  }
}
```


