# 26_ElasticSearch ���ֳ�������ضȷ����Ż�����

����ض����ֽ��е��ں��Ż��ĳ�����4�ַ���
* 1��query-time boost ��ѯ��ʱ������query��boost. ����Ȩ��
* 2���ع���ѯ�ṹ.��should��Ƕ��bool��
* 3��negative boost ������negative term��doc����������negative boost����������
* 4��constant_score �����ѹ��������Ҫ��ض����֣�ֱ����constant_score��filter�����е�doc��������1��û�����ֵĸ�����

## 1��query-time boost

```java
GET /forum/article/_search
{
  "query": {
    "bool": {
      "should": [
        {
          "match": {
            "title": {
              "query": "java spark",
              "boost": 2
            }
          }
        },
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

## 2���ع���ѯ�ṹ

�ع���ѯ�������es�°汾�У�Ӱ��Խ��ԽС�ˡ�һ������£�ûʲô��Ҫ�Ļ�����Ҳ���Ҳ�С�

```java
GET /forum/article/_search 
{
  "query": {
    "bool": {
      "should": [
        {
          "match": {
            "content": "java"  1/3
          }
        },
        {
          "match": {
            "content": "spark"  1/3
          }
        },
        {
          "bool": {
            "should": [
              {
                "match": {
                  "content": "solution"  1/6
                }
              },
              {
                "match": {
                  "content": "beginner"  1/6
                }
              }
            ]
          }
        }
      ]
    }
  }
}
```

## 3��negative boost

* ��������java��������spark��doc�����������Ӻ�����
* ��������java������������spark��doc�����������spark������˵�ų������doc������˵�����doc�ķ�������
* ������negative term��doc����������negative boost����������

```java
GET /forum/article/_search 
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "content": "java"
          }
        }
      ],
      "must_not": [
        {
          "match": {
            "content": "spark"
          }
        }
      ]
    }
  }
}
```

```java
GET /forum/article/_search 
{
  "query": {
    "boosting": {
      "positive": {
        "match": {
          "content": "java"
        }
      },
      "negative": {
        "match": {
          "content": "spark"
        }
      },
      "negative_boost": 0.2
    }
  }
}
```


negative��doc�������negative_boost�����ͷ���

## 4��constant_score

�����ѹ��������Ҫ��ض����֣�ֱ����constant_score��filter�����е�doc��������1��û�����ֵĸ�����

```java
GET /forum/article/_search 
{
  "query": {
    "bool": {
      "should": [
        {
          "constant_score": {
            "query": {
              "match": {
                "title": "java"
              }
            }
          }
        },
        {
          "constant_score": {
            "query": {
              "match": {
                "title": "spark"
              }
            }
          }
        }
      ]
    }
  }
}
```
