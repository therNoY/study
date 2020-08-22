# 6_ElatisSearch ����ȫ�ļ�������ľ�׼��

## ����
* 1��ȫ�ļ�����ʱ�򣬽��ж��ֵ�ļ�����������������match query��should
* 2���������������׼�ȣ�and operator��minimum_should_match

## ����
### 1��Ϊ�����������ӱ��⣨title���ֶ�

```java
POST /forum/article/_bulk
{ "update": { "_id": "1"} }
{ "doc" : {"title" : "this is java and elasticsearch blog"} }
{ "update": { "_id": "2"} }
{ "doc" : {"title" : "this is java blog"} }
{ "update": { "_id": "3"} }
{ "doc" : {"title" : "this is elasticsearch blog"} }
{ "update": { "_id": "4"} }
{ "doc" : {"title" : "this is java, elasticsearch, hadoop blog"} }
{ "update": { "_id": "5"} }
{ "doc" : {"title" : "this is spark blog"} }
```

### 2�����������а���java��elasticsearch��blog

* ������͸�֮ǰ���Ǹ�term query����һ���ˡ���������exact value���ǽ���full textȫ�ļ�����
* match query���Ǹ������ȫ�ļ����ġ���Ȼ�����Ҫ������field����not_analyzed���͵ģ���ômatch queryҲ�൱��term query��

GET /forum/article/_search
{
    "query": {
        "match": {
            "title": "java elasticsearch"
        }
    }
}

### 3�����������а���java��elasticsearch��blog

* �����е����±��� ͬʱ���� java��elasticsearch
* ʹ��and�ؼ��֣��������ϣ�����е������ؼ��ֶ�Ҫƥ��ģ���ô����and������ʵ�ֵ���match query�޷�ʵ�ֵ�Ч��

```java
GET /forum/article/_search
{
    "query": {
        "match": {
            "title": {
				"query": "java elasticsearch",
				"operator": "and"
			}
        }
    }
}
```

### 4����������java��elasticsearch��spark��hadoop��4���ؼ����У�����3����blog

* ָ��һЩ�ؼ����У���������ƥ�����еĶ��ٸ��ؼ��֣�������Ϊ�������

```java
GET /forum/article/_search
{
  "query": {
    "match": {
      "title": {
        "query": "java elasticsearch spark hadoop",
        "minimum_should_match": "75%"
      }
    }
  }
}
```

### 5����bool��϶������������������title

```java
GET /forum/article/_search
{
  "query": {
    "bool": {
      "must":     { "match": { "title": "java" }},
      "must_not": { "match": { "title": "spark"  }},
      "should": [
                  { "match": { "title": "hadoop" }},
                  { "match": { "title": "elasticsearch"   }}
      ]
    }
  }
}
```

### 6��bool��϶��������������μ���relevance score

must��should������Ӧ�ķ�����������������must��should������

* ������һ��java��ͬʱ����should�����еĹؼ��֣�hadoop��elasticsearch
* �����ڶ���java��ͬʱ����should�е�elasticsearch
* ����������java��������should�е��κιؼ���

should�ǿ���Ӱ����ضȷ�����

* must��ȷ��˵��˭����������ؼ��֣�ͬʱ��������must������ȥ�����document���������������relevance score
* ������must�Ļ���֮�ϣ�should�е���������ƥ��Ҳ���ԣ��������ƥ��ĸ��࣬��ôdocument��relevance score�ͻ����

�����Ľ����

```java
{
  "took": 6,
  "timed_out": false,
  "_shards": {
    "total": 5,
    "successful": 5,
    "failed": 0
  },
  "hits": {
    "total": 3,
    "max_score": 1.3375794,
    "hits": [
      {
        "_index": "forum",
        "_type": "article",
        "_id": "4",
        "_score": 1.3375794,
        "_source": {
          "articleID": "QQPX-R-3956-#aD8",
          "userID": 2,
          "hidden": true,
          "postDate": "2017-01-02",
          "tag": [
            "java",
            "elasticsearch"
          ],
          "tag_cnt": 2,
          "view_cnt": 80,
          "title": "this is java, elasticsearch, hadoop blog"
        }
      },
      {
        "_index": "forum",
        "_type": "article",
        "_id": "1",
        "_score": 0.53484553,
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
          "title": "this is java and elasticsearch blog"
        }
      },
      {
        "_index": "forum",
        "_type": "article",
        "_id": "2",
        "_score": 0.19856805,
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
          "title": "this is java blog"
        }
      }
    ]
  }
}
```


### 7������java��hadoop��spark��elasticsearch�����ٰ�������3���ؼ���

* Ĭ������£�should�ǿ��Բ�ƥ���κ�һ���ģ���������������У�this is java blog���Ͳ�ƥ���κ�һ��should����
* �����и��������������û��must�Ļ�����ôshould�б�������ƥ��һ���ſ���
* ���������������should����4��������Ĭ������£�ֻҪ��������һ���������Ϳ���ƥ����Ϊ�������
* ���ǿ��Ծ�׼���ƣ�should��4�������У�����ƥ�伸��������Ϊ�������

```java
GET /forum/article/_search
{
  "query": {
    "bool": {
      "should": [
        { "match": { "title": "java" }},
        { "match": { "title": "elasticsearch"   }},
        { "match": { "title": "hadoop"   }},
	{ "match": { "title": "spark"   }}
      ],
      "minimum_should_match": 3 
    }
  }
}
```



