# 27_ElasticSearch��function_score�Զ�����ضȷ����㷨 

## ����
* 1����field: tile �� content �в��� java spark ��doc
* 2��Ҫ��follower_numԽ��� doc ����Խ�ߡ�(�����ӵ���Խ�࣬��ô���ӵķ�����Խ��)

function_score������
* ���ǿ��������Զ���һ��function_score����
* �Լ���ĳ��field��ֵ����es����������ķ�����������
* Ȼ�����Լ�ָ����field�����з�������ǿ

## ����


�����е�������������follower����

```java
POST /forum/article/_bulk
{ "update": { "_id": "1"} }
{ "doc" : {"follower_num" : 5} }
{ "update": { "_id": "2"} }
{ "doc" : {"follower_num" : 10} }
{ "update": { "_id": "3"} }
{ "doc" : {"follower_num" : 25} }
{ "update": { "_id": "4"} }
{ "doc" : {"follower_num" : 3} }
{ "update": { "_id": "5"} }
{ "doc" : {"follower_num" : 60} }
```

* �������������õ��ķ�������follower_num�������㣬��follower_num��һ���̶�����ǿ���ӵķ���
* �����ӵ���Խ�࣬��ô���ӵķ�����Խ��

```java
GET /forum/article/_search
{
  "query": {
    "function_score": {
      "query": {
        "multi_match": {
          "query": "java spark",
          "fields": ["tile", "content"]
        }
      },
      "field_value_factor": {
        "field": "follower_num",
        "modifier": "log1p",
        "factor": 0.5
      },
      "boost_mode": "sum",
      "max_boost": 2
    }
  }
}
```

* field_value_factor�����ֻ��field����ô�Ὣÿ��doc�ķ���������follower_num������е�doc follower��0����ô�����ͻ��Ϊ0��Ч���ܲ��á�
* ���һ���Ӹ�log1p��������ʽ���Ϊ��new_score = old_score * log(1 + number_of_votes)�����������ķ�����ȽϺ���
* �ټӸ�factor�����Խ�һ��Ӱ�������new_score = old_score * log(1 + factor * number_of_votes)
* boost_mode�����Ծ���������ָ���ֶε�ֵ��μ��㣬multiply��sum��min��max��replace
* max_boost�����Ƽ�������ķ�����Ҫ����max_boostָ����ֵ


