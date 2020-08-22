# 8_����boost����������Ȩ�ؿ���

## ����

>* ���������а���java������
>* ͬʱ����������а���hadoop��elasticsearch��������������
>* ͬʱ�����һ�����Ӱ���java hadoop��һ�����Ӱ���java elasticsearch������hadoop������Ҫ��elasticsearch������������

## ֪ʶ��

* ����������Ȩ�أ�boost�����Խ�ĳ������������Ȩ�ؼӴ󣬴�ʱ��ƥ���������������ƥ����һ������������document
* ֪ʶ�����relevance scoreʱ��ƥ��Ȩ�ظ��������������document��relevance score����ߣ���ȻҲ�ͻ����ȱ����ػ���
* Ĭ������£�����������Ȩ�ض���һ���ģ�����1

## ����

```java
GET /forum/article/_search 
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "title": "blog"
          }
        }
      ],
      "should": [
        {
          "match": {
            "title": {
              "query": "java"
            }
          }
        },
        {
          "match": {
            "title": {
              "query": "hadoop"
            }
          }
        },
        {
          "match": {
            "title": {
              "query": "elasticsearch"
            }
          }
        },
        {
          "match": {
            "title": {
              "query": "spark",
              "boost": 5
            }
          }
        }
      ]
    }
  }
}
```



