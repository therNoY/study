# 20_ElasticSearch rescoring�����Ż�����ƥ������������

## һ������

* rescore���ش��
* ��match ��ѯ�Ľ���е�ǰ��������ʹ��proximity match ��֡�ԭ���� matchЧ����proximity match��50������proximity match ������������ľ���

## �����Ƚ� match��phrase match(proximity match)����

### 1��match

ֻҪ�򵥵�ƥ�䵽��һ��term���Ϳ�����⽫term��Ӧ��doc��Ϊ������أ�ɨ�赹��������ɨ�赽�˾�ok

### 2��phrase match
����ɨ�赽����term��doc list; �ҵ���������term��doc list; Ȼ���ÿ��doc������ÿ��term��position���Ƿ����ָ���ķ�Χ; slop����Ҫ���и��ӵ����㣬���ж��ܷ�ͨ��slop�ƶ���ƥ��һ��doc

### 3������
* match query�����ܱ�phrase match��proximity match����slop��Ҫ�ߺܶࡣ��Ϊ�����߶�Ҫ����position�ľ��롣
* match query��phrase match������Ҫ��10������proximity match������Ҫ��20����

���Ǳ�̫���ģ���Ϊes������һ�㶼�ں��뼶��match queryһ����ڼ����룬���߼�ʮ���룬��phrase match��proximity match�������ڼ�ʮ���뵽���ٺ���֮�䣬����Ҳ�ǿ��Խ��ܵġ�

## �����Ż�proximity match������

* �Ż�proximity match�����ܣ�һ����Ǽ���Ҫ����proximity match������document������
* ��Ҫ˼·���ǣ���match query�ȹ��˳���Ҫ������
* Ȼ������proximity match������term�������doc�ķ���
* ͬʱproximity matchֻ���ÿ��shard�ķ�������ǰn��doc�����ã������µ������ǵķ�����������̳�֮Ϊrescoring���ؼƷ֡�
* ��Ϊһ���û����ҳ��ѯ��ֻ�ῴ��ǰ��ҳ�����ݣ����Բ���Ҫ�����н������proximity match������

ʹ��match + proximity matchͬʱʵ���ٻ��ʺ;�׼��

## �ġ�����
* Ĭ������£�matchҲ��ƥ����1000��doc��proximity matchȫ����Ҫ��ÿ��doc����һ�����㣬�ж��ܷ�slop�ƶ�ƥ���ϣ�Ȼ��ȥ�����Լ��ķ���
* ���Ǻܶ�����£�match����Ҳ��1000��doc����ʵ�û��󲿷�������Ƿ�ҳ��ѯ�ģ����Կ������ֻ�ῴǰ��ҳ
* ����һҳ��10�������Ҳ��Ϳ�5ҳ������50��proximity matchֻҪ��ǰ50��doc����slop�ƶ�ȥƥ�䣬ȥ�����Լ��ķ������ɣ�����Ҫ��ȫ��1000��doc��ȥ���м���͹��׷���


rescore���ش�֣�
* match��1000��doc����ʵ��ʱ��ÿ��doc����һ��������; proximity match��ǰ50��doc������rescore���ش�֣�����; ��ǰ50��doc��term����Խ���ģ�����Խǰ��

```java

GET /forum/article/_search 
{
  "query": {
    "match": {
      "content": "java spark"
    }
  },
  "rescore": {
    "window_size": 50,
    "query": {
      "rescore_query": {
        "match_phrase": {
          "content": {
            "query": "java spark",
            "slop": 50
          }
        }
      }
    }
  }
}
```

