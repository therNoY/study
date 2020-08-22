# 22_ElasticSearch �����Ƽ�match_phrase_prefixʵ��search-time

## һ������

### 1.���ƽ���

�����Ƽ���search as you type��������ʾ��

* ��ٶ��������� elas ����ʾ --> elasticsearch  ��ʾ --> elasticsearchȨ��ָ��
* �� ���� hello w ����һ����ʾ

```java
hello world
hello we
hello win
hello wind
hello dog
hello cat
```
### 2.˵��

* ������Ҫ�ã���Ϊ�����һ��ǰ׺ʼ��Ҫȥɨ����������������ܿ��ܻ�ܲ�
*  max_expansions��ָ��prefix���ƥ����ٸ�term��������������Ͳ�����ƥ����

## �����﷨

```java
GET /my_index/my_type/_search 
{
  "query": {
    "match_phrase_prefix": {
      "title": "hello d"
    }
  }
}
```

## ����ԭ��

ԭ���match_phrase���ƣ�Ψһ�����𣬾��ǰ����һ��term��Ϊǰ׺ȥ������һ��������  hello w ���н���

* 1��hello����ȥ����match��������Ӧ��doc
* 2��w������Ϊǰ׺��ȥɨ�����������������ҵ�����w��ͷ��doc
* 3��Ȼ���ҵ�����doc�У�������hello���ְ���w��ͷ���ַ���doc
* 4���������slopȥ���㣬����slop��Χ�ڣ��ܲ�����hello w�����ø�doc�е�hello��w��ͷ�ĵ��ʵ�position��ƥ��

>* Ҳ����ָ��slop������ֻ�����һ��term����Ϊǰ׺
>* max_expansions��ָ��prefix���ƥ����ٸ�term��������������Ͳ�����ƥ���ˣ��޶�����
>* Ĭ������£�ǰ׺Ҫɨ�����еĵ��������е�term��ȥ����w��ͷ�ĵ��ʣ�������������̫�������max_expansions�޶���wǰ׺���ƥ����ٸ�term���Ͳ��ټ����������������ˡ�


