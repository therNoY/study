# 24_ElasticSearch TF&IDF�㷨�Լ������ռ�ģ��

## һ������
* boolean model ����and�����߼����������ȹ��˳�����ָ��term��doc
* TF ��term frequency�� һ��term��һ��doc�У����ֵĴ���Խ�࣬��ô��������ض����־ͻ�Խ��
* IDF��inversed document frequency�� һ��term�����е�doc�У����ֵĴ���Խ�࣬��ô��������ض����־ͻ�Խ��
* length norm  �������Ǹ�field�ĳ��ȣ�field����Խ����������ض�����Խ��; field����Խ�̣�������ض�����Խ��
* vector space model �����ռ�ģ��


## ����boolean model

����and�����߼����������ȹ��˳�����ָ��term��doc

* query "hello world" --> ���� --> hello / world / hello & world
* bool --> must/must not/should --> ���� --> ���� / ������ / ���ܰ���
* doc --> ������� --> ���� true or false --> Ϊ�˼��ٺ���Ҫ�����doc����������������

## ����TF/IDF

����term��doc�еķ���

* query: �����ؼ���hello world ��doc.content������

```java
doc1: java is my favourite programming language, hello world !!!
doc2: hello java, you are very good, oh hello world!!!
```

hello��doc1������

### 1��TF: term frequency 

* �ҵ�hello��doc1�г����˼��Σ�1�Σ�����ݳ��ֵĴ�����������
* һ��term��һ��doc�У����ֵĴ���Խ�࣬��ô��������ض����־ͻ�Խ��

### 2��IDF��inversed document frequency

* �ҵ�hello�����е�doc�г��ֵĴ�����3��
* һ��term�����е�doc�У����ֵĴ���Խ�࣬��ô��������ض����־ͻ�Խ��

### 3��length norm

* hello�������Ǹ�field�ĳ��ȣ�field����Խ����������ض�����Խ��; field����Խ�̣�������ض�����Խ��

### 4���ۺ�ƽ��

��󣬻Ὣhello���term����doc1�ķ������ۺ�TF��IDF��length norm���������һ���ۺ��Եķ���

```java

�����ؼ��֣�hello world 

--> doc1 
--> hello��doc1�ķ�����world��doc1�ķ��� 
--> �������hello world queryҪ��doc1��һ���ܵķ��� 
--> vector space model

```

## �ġ�vector space model �����ռ�ģ��


### 1�����ӣ�

���term��һ��doc���ܷ���

```java
hello world 
--> es�����hello world������doc�е���������������һ��query vector��query����
```

* hello���term�����Ļ�������doc��һ�����־���2
* world���term�����Ļ�������doc��һ�����־���5
* doc vector��3��doc��һ������1��term��һ��������һ��term��һ������2��term

```java
doc1������hello --> [2, 0]
doc2������world --> [0, 5]
doc3������hello, world --> [2, 5]
```

![image](https://github.com/csy512889371/learnDoc/blob/master/image/2018/es/2.png)

��ͼ��ȡÿ��doc vector��query vector�Ļ��ȣ�����ÿ��doc�Զ��term���ܷ���
* ÿ��doc vector�������query vector�Ļ��ȣ�������������ȸ���һ��doc�����query�ж��term���ܷ���
* ����Խ�󣬷����µ�; ����ԽС������Խ��
* ����Ƕ��term����ô�������Դ��������㣬�޷���ͼ��ʾ


