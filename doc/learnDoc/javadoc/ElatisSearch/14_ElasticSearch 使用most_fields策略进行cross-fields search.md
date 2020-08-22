# ElasticSearchʹ��most_fields���Խ���cross-fields search

## ����
* cross-fields������һ��Ψһ��ʶ�����˶��field��
* ����һ���ˣ���ʶ����������һ�����������ı�ʶ�ǵ�ַ����������ɢ���ڶ��field�У�����first_name��last_name�У���ַ����ɢ����country��province��city�С�
* ����field����һ����ʶ����������һ������������һ����ַ������cross-fields����
* ������˵�����Ҫʵ�֣�������most_fields�ȽϺ��ʡ���Ϊbest_fields��������������field��ƥ��Ľ����cross-fields����Ͳ���һ��field�������ˡ�

## ���ڵ����⣺

* ֻ���ҵ������ܶ��fieldƥ���doc��������ĳ��field��ȫƥ���doc
* most_fields��û�취��minimum_should_matchȥ����β���ݣ�����ƥ����ر��ٵĽ��
* TF/IDF�㷨������Peter Smith��Smith Williams������Peter Smith��ʱ������first_name�к�����Smith�ģ�����query������document�е�Ƶ�ʺܵͣ��õ��ķ����ܸߣ�����Smith Williams����������Peter Smithǰ��


## ����

�������ԣ�author_first_name��author_last_name

```java
POST /forum/article/_bulk
{ "update": { "_id": "1"} }
{ "doc" : {"author_first_name" : "Peter", "author_last_name" : "Smith"} }
{ "update": { "_id": "2"} }
{ "doc" : {"author_first_name" : "Smith", "author_last_name" : "Williams"} }
{ "update": { "_id": "3"} }
{ "doc" : {"author_first_name" : "Jack", "author_last_name" : "Ma"} }
{ "update": { "_id": "4"} }
{ "doc" : {"author_first_name" : "Robbin", "author_last_name" : "Li"} }
{ "update": { "_id": "5"} }
{ "doc" : {"author_first_name" : "Tonny", "author_last_name" : "Peter Smith"} }
```

most_fields��ʽʵ�ֲ�ѯ��

```java
GET /forum/article/_search
{
  "query": {
    "multi_match": {
      "query":       "Peter Smith",
      "type":        "most_fields",
      "fields":      [ "author_first_name", "author_last_name" ]
    }
  }
}
```

��ѯ�����

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
    "total": 3,
    "max_score": 0.6931472,
    "hits": [
      {
        "_index": "forum",
        "_type": "article",
        "_id": "2",
        "_score": 0.6931472,
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
          "author_last_name": "Williams"
        }
      },
      {
        "_index": "forum",
        "_type": "article",
        "_id": "1",
        "_score": 0.5753642,
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
          "sub_title": "learning more courses",
          "author_first_name": "Peter",
          "author_last_name": "Smith"
        }
      },
      {
        "_index": "forum",
        "_type": "article",
        "_id": "5",
        "_score": 0.51623213,
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
          "content": "spark is best big data solution based on scala ,an programming language similar to java",
          "sub_title": "haha, hello world",
          "author_first_name": "Tonny",
          "author_last_name": "Peter Smith"
        }
      }
    ]
  }
}
```

* ��ѯ��������� ����������Ҫ�ġ�
* Peter Smith��ƥ��author_first_name��ƥ�䵽��Smith����ʱ�����ķ����ܸߣ�Ϊʲô��
* ��ΪIDF�����ߣ�IDF����Ҫ�ߣ���ô���ƥ�䵽��term��Smith����������doc�еĳ���Ƶ��Ҫ�ͣ�author_first_name field�У�Smith�ͳ��ֹ�1��
* Peter Smith����ˣ�doc 1��Smith��author_last_name�У�����author_last_name����������Smith�����Ե���doc 1��IDF�����ϵ�

