# 1_ElatisSearchʹ��term filter����������

## 1�������û�ID���Ƿ����ء�����ID��������������������

### 1������һЩ������������

```xml
POST /forum/article/_bulk
{ "index": { "_id": 1 }}
{ "articleID" : "XHDK-A-1293-#fJ3", "userID" : 1, "hidden": false, "postDate": "2017-01-01" }
{ "index": { "_id": 2 }}
{ "articleID" : "KDKE-B-9947-#kL5", "userID" : 1, "hidden": false, "postDate": "2017-01-02" }
{ "index": { "_id": 3 }}
{ "articleID" : "JODL-X-1937-#pV7", "userID" : 2, "hidden": false, "postDate": "2017-01-01" }
{ "index": { "_id": 4 }}
{ "articleID" : "QQPX-R-3956-#aD8", "userID" : 2, "hidden": true, "postDate": "2017-01-02" }
```

* ������˵�����ȸ�4���ֶΣ���Ϊ����es��֧��json document��ʽ�ģ�����˵��չ�Ժ�����Էǳ�֮�á������������ҵ����������ӣ�Ҫ��document�����Ӹ����field����ô���ǿ��Ժܷ������ʱ���field��
* ����������ڹ�ϵ�����ݿ��У�����mysql�����ǽ�����һ��������Ҫ����������һЩcolumn���Ǿͺܿӵ��ˣ������ø��ӵ��޸ı�ṹ���﷨ȥִ�С����ҿ��ܶ�ϵͳ���뻹��һ����Ӱ�졣

```xml
GET /forum/_mapping/article

{
  "forum": {
    "mappings": {
      "article": {
        "properties": {
          "articleID": {
            "type": "text",
            "fields": {
              "keyword": {
                "type": "keyword",
                "ignore_above": 256
              }
            }
          },
          "hidden": {
            "type": "boolean"
          },
          "postDate": {
            "type": "date"
          },
          "userID": {
            "type": "long"
          }
        }
      }
    }
  }
}
```

����es 5.2�汾��type=text��Ĭ�ϻ���������field��һ����field��������articleID�����Ƿִʵģ�����һ���Ļ�������field.keyword��articleID.keyword��Ĭ�ϲ��ִʣ�����ౣ��256���ַ�

### 2�������û�ID��������

```xml
GET /forum/article/_search
{
    "query" : {
        "constant_score" : { 
            "filter" : {
                "term" : { 
                    "userID" : 1
                }
            }
        }
    }
}
```

* term filter/query���������ı����ִʣ�ֱ����ȥ����������ƥ�䣬���������ʲô����ȥƥ��ʲô
* ����˵������������ı����зִʵĻ�����helle world�� --> ��hello���͡�world���������ʷֱ�ȥ����������ƥ��
* term����hello world�� --> ��hello world����ֱ��ȥ����������ƥ�䡰hello world��

### 3������û�����ص�����

```xml
GET /forum/article/_search
{
    "query" : {
        "constant_score" : { 
            "filter" : {
                "term" : { 
                    "hidden" : false
                }
            }
        }
    }
}
```

### 4�����ݷ���������������

```xml
GET /forum/article/_search
{
    "query" : {
        "constant_score" : { 
            "filter" : {
                "term" : { 
                    "postDate" : "2017-01-01"
                }
            }
        }
    }
}
```

### 5����������ID��������

```xml

GET /forum/article/_search
{
    "query" : {
        "constant_score" : { 
            "filter" : {
                "term" : { 
                    "articleID" : "XHDK-A-1293-#fJ3"
                }
            }
        }
    }
}

{
  "took": 1,
  "timed_out": false,
  "_shards": {
    "total": 5,
    "successful": 5,
    "failed": 0
  },
  "hits": {
    "total": 0,
    "max_score": null,
    "hits": []
  }
}

GET /forum/article/_search
{
    "query" : {
        "constant_score" : { 
            "filter" : {
                "term" : { 
                    "articleID.keyword" : "XHDK-A-1293-#fJ3"
                }
            }
        }
    }
}

{
  "took": 2,
  "timed_out": false,
  "_shards": {
    "total": 5,
    "successful": 5,
    "failed": 0
  },
  "hits": {
    "total": 1,
    "max_score": 1,
    "hits": [
      {
        "_index": "forum",
        "_type": "article",
        "_id": "1",
        "_score": 1,
        "_source": {
          "articleID": "XHDK-A-1293-#fJ3",
          "userID": 1,
          "hidden": false,
          "postDate": "2017-01-01"
        }
      }
    ]
  }
}

```

* articleID.keyword����es���°汾���ý�����field�����ǲ��ִʵġ�
* 
* ����һ��articleID������ʱ�򣬻Ὠ������������һ�����Լ�������Ҫ�ִʵģ��ִʺ���뵹��������
* ����һ���ǻ���articleID.keyword�����ִʣ�����256���ַ���ֱ࣬��һ���ַ������뵹�������С�


* ����term filter����text���ˣ����Կ���ʹ�����õ�field.keyword������ƥ�䡣
* �����и����⣬Ĭ�Ͼͱ���256���ַ������Ծ����ܻ����Լ�ȥ�ֶ�����������ָ��not_analyzed�ɡ������°汾��es�У�����Ҫָ��not_analyzedҲ���ԣ���type=keyword���ɡ�

### 6���鿴�ִ�

```xml
GET /forum/_analyze
{
  "field": "articleID",
  "text": "XHDK-A-1293-#fJ3"
}
```

* Ĭ����analyzed��text���͵�field����������������ʱ�򣬾ͻ�����е�articleID�ִʣ��ִ��Ժ�ԭ����articleID��û���ˣ�ֻ�зִʺ�ĸ���word�����ڵ��������С�
* term���ǲ��������ı��ִʵģ�XHDK-A-1293-#fJ3 --> XHDK-A-1293-#fJ3������articleID����������ʱ��XHDK-A-1293-#fJ3 --> xhdk��a��1293��fj3

### 7���ؽ�����

```xml
DELETE /forum

PUT /forum
{
  "mappings": {
    "article": {
      "properties": {
        "articleID": {
          "type": "keyword"
        }
      }
    }
  }
}

POST /forum/article/_bulk
{ "index": { "_id": 1 }}
{ "articleID" : "XHDK-A-1293-#fJ3", "userID" : 1, "hidden": false, "postDate": "2017-01-01" }
{ "index": { "_id": 2 }}
{ "articleID" : "KDKE-B-9947-#kL5", "userID" : 1, "hidden": false, "postDate": "2017-01-02" }
{ "index": { "_id": 3 }}
{ "articleID" : "JODL-X-1937-#pV7", "userID" : 2, "hidden": false, "postDate": "2017-01-01" }
{ "index": { "_id": 4 }}
{ "articleID" : "QQPX-R-3956-#aD8", "userID" : 2, "hidden": true, "postDate": "2017-01-02" }

```

### 8�����¸�������ID�ͷ������ڽ�������

```xml

GET /forum/article/_search
{
    "query" : {
        "constant_score" : { 
            "filter" : {
                "term" : { 
                    "articleID" : "XHDK-A-1293-#fJ3"
                }
            }
        }
    }
}
```

## 2��֪ʶ��

* ��1��term filter������exact value�������������֡�boolean��date��Ȼ֧��
* ��2��text��Ҫ������ʱָ��Ϊnot_analyzed��������term query
* ��3���൱��SQL�еĵ���where����

```sql
select *
from forum.article
where articleID='XHDK-A-1293-#fJ3'
```

