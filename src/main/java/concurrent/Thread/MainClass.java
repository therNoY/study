package concurrent.Thread;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        List<Message>  messageList = new ArrayList<>();
        for(int i = 0;i<=10;i++){
            new Product(messageList).start();
            new Consumter(messageList).start();
        }
    }
}

class Product extends Thread {
    private List<Message> messageList;
    public Product(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public void run() {
        if (messageList.size() >= 5) {
            System.out.println("消息已满，等待消费者消费。。。");
            try {
                synchronized (messageList) {
                    messageList.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            Message message = new Message();
            message.setMesage(LocalDateTime.now().toString());
            System.out.println("记录生成"+message.getMesage());
            messageList.add(message);
            synchronized (messageList) {
                messageList.notifyAll();
            }
        }
    }
}

class Consumter extends Thread {
    private List<Message> messageList;
    public Consumter(List<Message> messageList) {
        this.messageList = messageList;
    }
    @Override
    public void run() {
        if (messageList.size() <= 0) {
            System.out.println("没有消息消费");
            try {
                synchronized (messageList) {
                    messageList.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("记录消费"+messageList.get(0).getMesage());
            messageList.remove(messageList.get(0));
            synchronized (messageList) {
                messageList.notifyAll();
            }
        }
    }
}
class Message{
    private String mesage;

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }
}

