# 23_ElsaticSearch �����Ƽ�ngram�ִʻ���ʵ��index-time

## һ������
* ����������ʱ��Ͷ�ÿ�����ʽ����з֣������������Ƽ���ʾ������ٶ�
* ʹ��edge ngram��ÿ�����ʶ����н�һ���ķִ��з֣����зֺ��ngram��ʵ��ǰ׺�����Ƽ�����
* ������ʱ�򣬲����ٸ���һ��ǰ׺��Ȼ��ɨ����������������; �򵥵���ǰ׺ȥ����������ƥ�伴�ɣ����ƥ�����ˣ���ô�ͺ���; 

## ����ngram��index-time�����Ƽ�ԭ��

### 1.ʲô��ngram������ʹ�õ���quick��

* quick��5�ֳ����µ�ngram����quick����ͬ���ȵ��з��磺
```java
ngram length=1��q u i c k
ngram length=2��qu ui ic ck
ngram length=3��qui uic ick
ngram length=4��quic uick
ngram length=5��quick
```

### 2.ʲô��edge ngram

quick��anchor����ĸ�����ngram�����������ʽ����з֣�

```java
q
qu
qui
quic
quick
```

ʹ��edge ngram��ÿ�����ʶ����н�һ���ķִ��з֣����зֺ��ngram��ʵ��ǰ׺�����Ƽ�����

### 3��min ngram��max ngram

* ָ���зָ���

min ngram = 1
max ngram = 3


### ��������

```java
PUT /my_index
{
    "settings": {
        "analysis": {
            "filter": {
                "autocomplete_filter": { 
                    "type":     "edge_ngram",
                    "min_gram": 1,
                    "max_gram": 20
                }
            },
            "analyzer": {
                "autocomplete": {
                    "type":      "custom",
                    "tokenizer": "standard",
                    "filter": [
                        "lowercase",
                        "autocomplete_filter" 
                    ]
                }
            }
        }
    }
}
```

```java
GET /my_index/_analyze
{
  "analyzer": "autocomplete",
  "text": "quick brown"
}
```

```java
PUT /my_index/_mapping/my_type
{
  "properties": {
      "title": {
          "type":     "string",
          "analyzer": "autocomplete",
          "search_analyzer": "standard"
      }
  }
}
```

```java
GET /my_index/my_type/_search 
{
  "query": {
    "match_phrase": {
      "title": "hello w"
    }
  }
}
```

* �����match��ֻ��hello��Ҳ�������ȫ�ļ�����ֻ�Ƿ����Ƚϵ�
* �Ƽ�ʹ��match_phrase��Ҫ��ÿ��term���У�����position�պÿ���1λ���������ǵ�������





