
����ϵͳ�������Լ� Host �ļ����໥����

```
hostnamectl set-hostname k8s-master01

hostnamectl set-hostname k8s-node01

hostnamectl set-hostname hub
```

��װ������

```
yum install -y conntrack ntpdate ntp ipvsadm ipset jq iptables curl sysstat libseccomp wget vim net-tools git

```

centos8:����

```
ntpdate ntp û����
```





�ļ��ϴ�

```
yum -y install lrzsz

rz -E
```


���÷���ǽΪ Iptables �����ÿչ���

```
systemctl stop firewalld && systemctl disable firewalld

yum -y install iptables-services && systemctl start iptables && systemctl enable iptables && iptables -F && service iptables save
```

�ر� SELINUX

```
swapoff -a && sed -i '/ swap / s/^\(.*\)$/#\1/g' /etc/fstab
setenforce 0 && sed -i 's/^SELINUX=.*/SELINUX=disabled/' /etc/selinux/config
```


�����ں˲��������� K8S

```
cat > kubernetes.conf <<EOF
net.bridge.bridge-nf-call-iptables=1
net.bridge.bridge-nf-call-ip6tables=1
net.ipv4.ip_forward=1
net.ipv4.tcp_tw_recycle=0
vm.swappiness=0 # ��ֹʹ�� swap �ռ䣬ֻ�е�ϵͳ OOM ʱ������ʹ����
vm.overcommit_memory=1 # ����������ڴ��Ƿ���
vm.panic_on_oom=0 # ���� OOM
fs.inotify.max_user_instances=8192
fs.inotify.max_user_watches=1048576
fs.file-max=52706963
fs.nr_open=52706963
net.ipv6.conf.all.disable_ipv6=1
net.netfilter.nf_conntrack_max=2310720
EOF

cp kubernetes.conf /etc/sysctl.d/kubernetes.conf

sysctl -p /etc/sysctl.d/kubernetes.conf
```

��ע��centos8��������

```
1:  modprobe br_netfilter
2:  Linux ��4.12�ں˰汾��ʼ�Ƴ��� tcp_tw_recycle ���á�

```





����ϵͳʱ��

```
# ����ϵͳʱ��Ϊ �й�/�Ϻ�
timedatectl set-timezone Asia/Shanghai
# ����ǰ�� UTC ʱ��д��Ӳ��ʱ��
timedatectl set-local-rtc 0
# ����������ϵͳʱ��ķ���
systemctl restart rsyslog
systemctl restart crond
```

�ر�ϵͳ����Ҫ(postfix)����
```
systemctl stop postfix && systemctl disable postfix
```



���� rsyslogd �� systemd journald

```
mkdir /var/log/journal # �־û�������־��Ŀ¼

mkdir /etc/systemd/journald.conf.d

cat > /etc/systemd/journald.conf.d/99-prophet.conf <<EOF
[Journal]
# �־û����浽����
Storage=persistent
# ѹ����ʷ��־
Compress=yes
SyncIntervalSec=5m
RateLimitInterval=30s
RateLimitBurst=1000
# ���ռ�ÿռ� 10G
SystemMaxUse=10G
# ����־�ļ���� 200M
SystemMaxFileSize=200M
# ��־����ʱ�� 2 ��
MaxRetentionSec=2week
# ������־ת���� syslog
ForwardToSyslog=no
EOF

systemctl restart systemd-journald
```

����ϵͳ�ں�Ϊ 4.44
CentOS 7.x ϵͳ�Դ��� 3.10.x �ں˴���һЩ Bugs���������е� Docker��Kubernetes ���ȶ������磺 rpm -Uvh
http://www.elrepo.org/elrepo-release-7.0-3.el7.elrepo.noarch.rpm


```
rpm -Uvh http://www.elrepo.org/elrepo-release-7.0-3.el7.elrepo.noarch.rpm
# ��װ��ɺ��� /boot/grub2/grub.cfg �ж�Ӧ�ں� menuentry ���Ƿ���� initrd16 ���ã����û�У��ٰ�װ
һ�Σ�
yum --enablerepo=elrepo-kernel install -y kernel-lt

```

```
�༭ /etc/default/grub �ļ�
���� GRUB_DEFAULT=0
```

���� grub �����ļ�������

```
grub2-mkconfig -o /boot/grub2/grub.cfg

reboot

uname -r
```


Centos7����֮����������

```
systemctl stop NetworkManager
systemctl disable NetworkManager

service network restart
```



















