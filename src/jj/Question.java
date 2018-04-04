package jj;  
import java.awt.Container;  
import java.awt.Dimension;  
import java.awt.Font;  
import java.awt.GridLayout;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.KeyAdapter;  
import java.awt.event.KeyEvent;  
import java.text.DecimalFormat;  
import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.Map;  
import java.util.Set;  
  
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JOptionPane;  
import javax.swing.JPanel;  
import javax.swing.JPasswordField;  
import javax.swing.JTextField;  

public class Question {  
public static void main(String[] args) {  
//��¼����¼�ɹ������д���ϵͳ  
    login();  
}     
//����ȫ�ֵķ���  
static int score=0;  
//����ȫ�ֵ�value���0  
static float currValue;  
//����ȫ������ʽ���ַ���  
static String currString;  
//����ȫ��ʱ��  
static String currTime;  
//����ȫ����Ŀ��  
static int currNums=0;  
/**1.���ߴ������**/  
public static void exercise(){  
    // TODO Auto-generated method stub  
    JFrame f=new JFrame();  
    f.setTitle("���ߴ���ϵͳ(����һλС��)");  
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    f.setExtendedState(JFrame.MAXIMIZED_BOTH);  
    f.setVisible(true);  
  
    //���ô��ڵĴ�С��λ��  
    f.setSize(600,500);  
    f.setLocation(400,100);  
    f.setLayout(null);  
      
    JPanel pan1=new JPanel();  
    JLabel name1=new JLabel("7/7=");  
    pan1.add(name1);  
    JTextField  nameTest1=new JTextField(15);  
    nameTest1.setBounds(10, 10, 180, 100);  
    //  nameTest1.setPreferredSize(new Dimension(180,100));  
    pan1.add(nameTest1);  
    pan1.setBounds(10, 10, 200, 120);  
    f.add(pan1);  
  
      
    JPanel pan2=new JPanel();  
    JLabel name2=new JLabel("������������");  
    pan2.add(name2);  
    JTextField nameTest2=new JTextField(15);  
    nameTest2.setBounds(300, 10, 180, 100);  
    //  nameTest2.setPreferredSize(new Dimension(180,100));  
    pan2.add(nameTest2);  
    pan2.setBounds( 300,10, 200, 120);  
    f.add(pan2);  
  
    JPanel pan3=new JPanel();  
    pan3.setLayout(null);  
    JTextField nameTest3=new JTextField(15);  
    nameTest3.setBounds(10, 60, 480, 200);  
    nameTest3.setPreferredSize(new Dimension(300,100));  
    pan3.add(nameTest3);  
    pan3.setBounds( 10,60, 500, 220);  
    f.add(pan3);  
      
    JPanel pan4 = new JPanel();  
//  pan4.setLayout(null);  
    JButton btnStart=new JButton("��ʼ");  
    btnStart.setBounds(30, 300,30, 30);  
    btnStart.setPreferredSize(new Dimension(100,80));  
    btnStart.addActionListener(new ActionListener() {  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            Map<String,Float> map=new HashMap<String,Float>();  
            map=operation();  
            Set set=map.keySet();  
            Iterator iter = set.iterator();  
            String key = (String) iter.next();    
            float value=map.get(key);  
            if(btnStart.getText().equals("��ʼ")){  
            currNums+=1;  
            btnStart.setText("��ͣ");  
            name1.setText(key);  
            System.out.println("value:"+value);  
            currValue=value;  
            currString=key;  
            map.clear();  
            }else{  
                btnStart.setText("��ʼ");  
                nameTest1.addKeyListener(new KeyAdapter() {  
                public void keyPressed(KeyEvent e) {  
                      //���س���ִ����Ӧ����;  
                    if(e.getKeyChar()==KeyEvent.VK_ENTER ){  
                        String answerStr=nameTest1.getText();  
                        float answer=Float.parseFloat(answerStr);  
                        //�ж����󣬽��мӷ֣�����ʾ  
                        System.out.println("answer:"+answer);  
                        System.out.println("value:"+currValue);  
                        if(answer==currValue){  
                            score+=5;  
                            nameTest3.setFont(new Font("����", Font.PLAIN, 20));  
                            nameTest3.setText("����Ϊ:"+currString+""+currValue+" ||  ���Ļش���ȷ || ��ǰ����:"+score);  
                            nameTest1.setText("");  
                        }else{  
                            nameTest3.setFont(new Font("����", Font.PLAIN, 20));  
                            nameTest3.setText("����Ϊ:"+currString+""+currValue+" ||  ���Ļش���� || ��ǰ����:"+score);  
                        }  
                    }   
                };  
                });  
            }  
        }  
    });  
    pan4.add(btnStart);  
    pan4.setBounds(40, 350, 110, 90);  
    f.add(pan4);  
      
    JPanel pan5 = new JPanel();  
//  pan4.setLayout(null);  
    JButton btnStart1=new JButton("��ʱ");  
    btnStart1.setBounds(30, 300,30, 30);  
    btnStart1.setPreferredSize(new Dimension(100,80));  
    btnStart1.addActionListener(new ActionListener() {  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            if(btnStart1.getText().equals("��ʱ")){  
            btnStart1.setText("���ڼ�ʱ...");  
            nameTest3.setFont(new Font("����", Font.PLAIN, 20));  
            nameTest3.setText("              ��ʱ��ʼ�����������");  
            //��ȡ��ǰʱ��  
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            currTime=df.format(new Date());  
            }  
        }  
    });  
    pan5.add(btnStart1);  
    pan5.setBounds(190, 350, 110, 90);  
    f.add(pan5);  
      
    JPanel pan6 = new JPanel();  
//  pan4.setLayout(null);  
    JButton btnStart2=new JButton("����");  
    btnStart2.setBounds(30, 300,30, 30);  
    btnStart2.setPreferredSize(new Dimension(100,80));  
    btnStart2.addActionListener(new ActionListener() {  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            //������ʱ  
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            String endTime=df.format(new Date());  
            try {  
                long start =df.parse(currTime).getTime();  
                long end=df.parse(endTime).getTime();  
                int minutes = (int) ((end - start)/(1000 ));  
                nameTest3.setFont(new Font("����", Font.PLAIN, 20));  
                nameTest3.setText("ʱ��:"+minutes+"��  ||"+" һ��������"+currNums+"����  ||  �ܵ÷�:"+score);  
            } catch (ParseException e1) {  
                e1.printStackTrace();  
            }  
        }  
    });  
    pan6.add(btnStart2);  
    pan6.setBounds(340, 350, 110, 90);  
    f.add(pan6);  
      
}  
/**2.������������**/  
public static Map<String,Float> operation(){  
    Map<String,Float> map=new HashMap<String, Float>();  
    String[] operators={"+","-","x","/"};  
    int x=(int)(Math.random()*100);  
    int y=(int)(Math.random()*100+1); 
    
    int index=(int)(Math.random()*4);  
    while(index==4){  
        index=(int)Math.random()*4;  
    }  
    String operator=operators[index];  
    String formula=(x+"")+operator+(""+y)+"=";  
    float result=0;  
    if(operator.equals("+")){  
        result=x+y;  
        map.put(formula, result);  
          
    }else if(operator.equals("-")){  
        result=x-y;  
        map.put(formula, result);  
    }else if(operator.equals("x")){  
        result=x*y;  
        map.put(formula, result);  
    }else{  
        DecimalFormat df=new DecimalFormat("0.0");  
        String nums=df.format((float)x/y);  
        result=Float.parseFloat(nums);  
        map.put(formula, result);  
    }  
      
    return map;  
}  
  
/**2.��¼��ת����**/  
public static  void login(){  
    // TODO Auto-generated method stub  
    JFrame f=new JFrame();  
    f.setTitle("ϵͳ��¼����");  
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    f.setExtendedState(JFrame.MAXIMIZED_BOTH);  
    f.setVisible(true);  
  
    //���ô��ڵĴ�С��λ��  
    f.setSize(400,400);  
    f.setLocation(420,120);  
  
    Container con=f.getContentPane();//����һ������     
    con.setLayout(new GridLayout(7,1));  
    //����һ���µİ���  
    JPanel pan1=new JPanel();  
    JLabel title=new JLabel("��ӭ��½��ϵͳ");  
    title.setFont(new Font("����",Font.BOLD, 20));  
    pan1.add(title);  
    con.add(pan1);  
      
    //������ĵ�½����  
    //����һ���µİ���  
    JPanel pan2=new JPanel();  
    JLabel name=new JLabel("�û���");  
    pan2.add(name);  
    JTextField nameTest=new JTextField(15);  
    pan2.add(nameTest);  
    con.add(pan2);  
    //�û��������ı�������ڵڶ���������  
  
    //����һ���µİ���  
    JPanel pan3=new JPanel();  
    JLabel pass = new JLabel("����");  
    pan3.add(pass);  
    JPasswordField password=new JPasswordField(15);  
    password.setEchoChar('*');  
    pan3.add(password);  
    con.add(pan3);  
    //���뼰����������ڵ�����������  
  
 // System.out.println(username+"   "+userPassword);  
      
    JPanel pan4 = new JPanel();  
    JButton b_log=new JButton("��½");  
    b_log.addActionListener(new ActionListener() {  
        public void actionPerformed(ActionEvent e) {  
            // TODO Auto-generated method stub  
            //��ȡ�û��������룬����У��  
            String myUsername=nameTest.getText();  
            String myPassword=password.getText();  
            if(myUsername.equals("MJ")&&myPassword.equals("123456")){  
                JOptionPane.showMessageDialog(null, "��½�ɹ�!");  
                exercise();  
                //              System.exit(0);  
  
            }else{  
                JOptionPane.showMessageDialog(null, "�˺Ż��������!");  
                nameTest.setText("");  
                password.setText("");  
  
            }  
              
        }  
    });  
    pan4.add(b_log);  
      
  
    JButton b_exit=new JButton("�˳�");  
    pan4.add(b_exit);   
    con.add(pan4);  
    //��½���˳���������ť���ڵ��ĸ�������  
  
    JPanel pan5 = new JPanel();  
    con.add(pan5);  
    JPanel pan6 = new JPanel();  
    con.add(pan6);  
    JPanel pan7 = new JPanel();  
    con.add(pan7);  
    //�հװ���  
  
}     
  
        }  
      
