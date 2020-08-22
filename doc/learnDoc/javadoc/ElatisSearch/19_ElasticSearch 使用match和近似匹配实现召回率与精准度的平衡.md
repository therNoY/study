# 19_ElasticSearch ʹ��match�ͽ���ƥ��ʵ���ٻ����뾫׼�ȵ�ƽ��


## һ����Ҫ

### 1.�ٻ���
����������һ��java spark���ܹ���100��doc���ܷ��ض��ٸ�doc��Ϊ����������ٻ��ʣ�recall

### 2.��׼��
����������һ��java spark���ܲ��ܾ������ð���java spark��������java��spark��ĺܽ���doc��������ǰ�棬precision

### 3.����

* ������ʱ��������ϣ������ƥ�䵽����term�еĲ��֣��Ϳ�����Ϊ���������������������ٻ��ʡ�
* ͬʱ����Ҳϣ������match_phrase���ݾ������������Ĺ��ܣ��ü���term����Խ��������Խ�ߣ����ȷ���
* �������������ٻ��ʣ���˼��java spark������java��Ҳ���أ�����spark��Ҳ���أ�����java��spark��Ҳ���أ�
* ͬʱ��˾�׼�ȣ����ǰ���java��spark��ͬʱjava��spark���Խ����doc������ǰ��

### 4.�������

* ֱ����match_phrase�����������ᵼ�±�������term����doc field�г��֣����Ҿ�����slop�޶���Χ�ڣ�����ƥ����
* match phrase��proximity match��Ҫ��doc����������е�term��������Ϊ������أ����ĳһ��doc���ܾ�����ĳ��termû�а�������ô���޷���Ϊ�������

```java
java spark --> hello world java --> �Ͳ��ܷ�����
java spark --> hello world, java spark --> �ſ��Է���
```

* ����ƥ���ʱ���ٻ��ʱȽϵͣ���׼��̫����


## ����ƽ���ٻ����뾫׼��

��ʱ������bool���match query��match_phrase queryһ����ʵ������Ч��

### 1����ѯ�﷨˵��

```java
GET /forum/article/_search
{
  "query": {
    "bool": {
      "must": {
        "match": { 
          "title": {
            "query":                "java spark" --> java��spark��java spark��java��spark��ǰ������û������java��spark�ľ��룬Ҳ��java��spark���ĺܽ�������û��������ǰ��
          }
        }
      },
      "should": {
        "match_phrase": { --> ��slop���ڣ����java spark��ƥ����һ��doc����ô�ͻ��doc�����Լ���relevance score�����java��spark����Խ������ô�ͷ���Խ��
          "title": {
            "query": "java spark",
            "slop":  50
          }
        }
      }
    }
  }
}
```

### ����һ

��ʹ�� match��ѯ

```java
GET /forum/article/_search 
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "content": "java spark"
          }
        }
      ]
    }
  }
}
```

match��ѯ���

```java

{
  "took": 5,
  "timed_out": false,
  "_shards": {
    "total": 5,
    "successful": 5,
    "failed": 0
  },
  "hits": {
    "total": 2,
    "max_score": 0.68640786,
    "hits": [
      {
        "_index": "forum",
        "_type": "article",
        "_id": "2",
        "_score": 0.68640786,
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
          "new_author_first_name": "Smith",
          "followers": [
            "Tom",
            "Jack"
          ]
        }
      },
      {
        "_index": "forum",
        "_type": "article",
        "_id": "5",
        "_score": 0.68324494,
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
          "new_author_first_name": "Tonny",
          "followers": [
            "Jack",
            "Robbin Li"
          ]
        }
      }
    ]
  }
}
```

### ���Ӷ� ʹ�� match�� match_phrase ��ϲ�ѯ


```java
GET /forum/article/_search 
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "content": "java spark"
          }
        }
      ],
      "should": [
        {
          "match_phrase": {
            "content": {
              "query": "java spark",
              "slop": 50
            }
          }
        }
      ]
    }
  }
}
```


match�� match_phrase ��ϲ�ѯ�����


```java

{
  "took": 5,
  "timed_out": false,
  "_shards": {
    "total": 5,
    "successful": 5,
    "failed": 0
  },
  "hits": {
    "total": 2,
    "max_score": 1.258609,
    "hits": [
      {
        "_index": "forum",
        "_type": "article",
        "_id": "5",
        "_score": 1.258609,
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
          "new_author_first_name": "Tonny",
          "followers": [
            "Jack",
            "Robbin Li"
          ]
        }
      },
      {
        "_index": "forum",
        "_type": "article",
        "_id": "2",
        "_score": 0.68640786,
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
          "new_author_first_name": "Smith",
          "followers": [
            "Tom",
            "Jack"
          ]
        }
      }
    ]
  }
}
```

