# 16_ElasticSearch ʹ��ԭ��cross-fields ��ѯ

# ����

## ����

```java
GET /forum/article/_search
{
  "query": {
    "multi_match": {
      "query": "Peter Smith",
      "type": "cross_fields", 
      "operator": "and",
      "fields": ["author_first_name", "author_last_name"]
    }
  }
}

```

## ʹ��most_fields ʵ��cross-fields���ڵ����⣺

* ֻ���ҵ������ܶ��fieldƥ���doc��������ĳ��field��ȫƥ���doc
* most_fields��û�취��minimum_should_matchȥ����β���ݣ�����ƥ����ر��ٵĽ��
* TF/IDF�㷨������Peter Smith��Smith Williams������Peter Smith��ʱ������first_name�к�����Smith�ģ�����query������document�е�Ƶ�ʺܵͣ��õ��ķ����ܸߣ�����Smith Williams����������Peter Smithǰ��

## ʹ��ԭ��cross-fields ��ѯ�������������:

����1��ֻ���ҵ������ܶ��fieldƥ���doc��������ĳ��field��ȫƥ���doc 

> �����Ҫ��ÿ��term���������κ�һ��field�г���Peter��Smith

* Ҫ��Peter������author_first_name��author_last_name�г���
* Ҫ��Smith������author_first_name��author_last_name�г���

* Peter Smith�����Ǻ���ڶ��field�еģ����Ա���Ҫ��ÿ��term����ĳ��field�г��֣���������������������Ҫ�ı�ʶ������������
* ԭ��most_fiels��������Smith WilliamsҲ���ܻ���֣���Ϊmost_fieldsҪ��ֻ���κ�һ��fieldƥ���˾Ϳ��ԣ�ƥ���fieldԽ�࣬����Խ��


����2��most_fields��û�취��minimum_should_matchȥ����β���ݣ�����ƥ����ر��ٵĽ�� 

> �������Ȼÿ��term��Ҫ����֣���β�϶���ȥ������

* java hadoop spark --> ��3��term���������κ�һ��field������
* �����е�document��ֻ��һ��field�а���һ��java���Ǿͱ��ɵ��ˣ���Ϊ��β��û��

����3��TF/IDF�㷨������Peter Smith��Smith Williams������Peter Smith��ʱ������first_name�к�����Smith�ģ�����query������document�е�Ƶ�ʺܵͣ��õ��ķ����ܸߣ�����Smith Williams����������Peter Smithǰ�� 

> ����IDF��ʱ�򣬽�ÿ��query��ÿ��field�е�IDF��ȡ������ȡ��Сֵ���Ͳ�����ּ�������µļ���ֵ��

˵����

```xml
Peter Smith
1�� Peter
2�� Smith
```

* Smith����author_first_name���field�У�������doc�����Field�У����ֵ�Ƶ�ʺܵͣ�����IDF�����ܸߣ�
* Smith������doc��author_last_name field�е�Ƶ�����һ��IDF��������Ϊһ����˵last_name�е�SmithƵ�ʶ��ϸߣ�����IDF�����������ģ�����̫�ߣ�
* Ȼ�����Smith��˵����ȡ����IDF�����У���С���Ǹ��������Ͳ������IDF�ֹ��ߵ������

