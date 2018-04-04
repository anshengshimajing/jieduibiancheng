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
//登录，登录成功后运行答题系统  
    login();  
}     
//定义全局的分数  
static int score=0;  
//定义全局的value结果0  
static float currValue;  
//定义全局运算式的字符串  
static String currString;  
//定义全局时间  
static String currTime;  
//定义全局题目数  
static int currNums=0;  
/**1.在线答题界面**/  
public static void exercise(){  
    // TODO Auto-generated method stub  
    JFrame f=new JFrame();  
    f.setTitle("在线答题系统(保留一位小数)");  
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    f.setExtendedState(JFrame.MAXIMIZED_BOTH);  
    f.setVisible(true);  
  
    //设置窗口的大小和位置  
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
    JLabel name2=new JLabel("请输入秒数：");  
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
    JButton btnStart=new JButton("开始");  
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
            if(btnStart.getText().equals("开始")){  
            currNums+=1;  
            btnStart.setText("暂停");  
            name1.setText(key);  
            System.out.println("value:"+value);  
            currValue=value;  
            currString=key;  
            map.clear();  
            }else{  
                btnStart.setText("开始");  
                nameTest1.addKeyListener(new KeyAdapter() {  
                public void keyPressed(KeyEvent e) {  
                      //按回车键执行相应操作;  
                    if(e.getKeyChar()==KeyEvent.VK_ENTER ){  
                        String answerStr=nameTest1.getText();  
                        float answer=Float.parseFloat(answerStr);  
                        //判断正误，进行加分，并显示  
                        System.out.println("answer:"+answer);  
                        System.out.println("value:"+currValue);  
                        if(answer==currValue){  
                            score+=5;  
                            nameTest3.setFont(new Font("宋体", Font.PLAIN, 20));  
                            nameTest3.setText("本题为:"+currString+""+currValue+" ||  您的回答正确 || 当前分数:"+score);  
                            nameTest1.setText("");  
                        }else{  
                            nameTest3.setFont(new Font("宋体", Font.PLAIN, 20));  
                            nameTest3.setText("本题为:"+currString+""+currValue+" ||  您的回答错误 || 当前分数:"+score);  
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
    JButton btnStart1=new JButton("计时");  
    btnStart1.setBounds(30, 300,30, 30);  
    btnStart1.setPreferredSize(new Dimension(100,80));  
    btnStart1.addActionListener(new ActionListener() {  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            if(btnStart1.getText().equals("计时")){  
            btnStart1.setText("正在计时...");  
            nameTest3.setFont(new Font("宋体", Font.PLAIN, 20));  
            nameTest3.setText("              计时开始，请认真答题");  
            //获取当前时间  
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
    JButton btnStart2=new JButton("结束");  
    btnStart2.setBounds(30, 300,30, 30);  
    btnStart2.setPreferredSize(new Dimension(100,80));  
    btnStart2.addActionListener(new ActionListener() {  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            //计算用时  
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            String endTime=df.format(new Date());  
            try {  
                long start =df.parse(currTime).getTime();  
                long end=df.parse(endTime).getTime();  
                int minutes = (int) ((end - start)/(1000 ));  
                nameTest3.setFont(new Font("宋体", Font.PLAIN, 20));  
                nameTest3.setText("时间:"+minutes+"秒  ||"+" 一共计算了"+currNums+"道题  ||  总得分:"+score);  
            } catch (ParseException e1) {  
                e1.printStackTrace();  
            }  
        }  
    });  
    pan6.add(btnStart2);  
    pan6.setBounds(340, 350, 110, 90);  
    f.add(pan6);  
      
}  
/**2.生成四则运算**/  
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
  
/**2.登录跳转方法**/  
public static  void login(){  
    // TODO Auto-generated method stub  
    JFrame f=new JFrame();  
    f.setTitle("系统登录界面");  
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    f.setExtendedState(JFrame.MAXIMIZED_BOTH);  
    f.setVisible(true);  
  
    //设置窗口的大小和位置  
    f.setSize(400,400);  
    f.setLocation(420,120);  
  
    Container con=f.getContentPane();//生成一个容器     
    con.setLayout(new GridLayout(7,1));  
    //生成一个新的版面  
    JPanel pan1=new JPanel();  
    JLabel title=new JLabel("欢迎登陆本系统");  
    title.setFont(new Font("宋体",Font.BOLD, 20));  
    pan1.add(title);  
    con.add(pan1);  
      
    //最上面的登陆文字  
    //生成一个新的版面  
    JPanel pan2=new JPanel();  
    JLabel name=new JLabel("用户名");  
    pan2.add(name);  
    JTextField nameTest=new JTextField(15);  
    pan2.add(nameTest);  
    con.add(pan2);  
    //用户名及其文本框放置在第二个版面上  
  
    //生成一个新的版面  
    JPanel pan3=new JPanel();  
    JLabel pass = new JLabel("密码");  
    pan3.add(pass);  
    JPasswordField password=new JPasswordField(15);  
    password.setEchoChar('*');  
    pan3.add(password);  
    con.add(pan3);  
    //密码及其密码域放在第三个版面上  
  
 // System.out.println(username+"   "+userPassword);  
      
    JPanel pan4 = new JPanel();  
    JButton b_log=new JButton("登陆");  
    b_log.addActionListener(new ActionListener() {  
        public void actionPerformed(ActionEvent e) {  
            // TODO Auto-generated method stub  
            //获取用户名和密码，进行校验  
            String myUsername=nameTest.getText();  
            String myPassword=password.getText();  
            if(myUsername.equals("MJ")&&myPassword.equals("123456")){  
                JOptionPane.showMessageDialog(null, "登陆成功!");  
                exercise();  
                //              System.exit(0);  
  
            }else{  
                JOptionPane.showMessageDialog(null, "账号或密码错误!");  
                nameTest.setText("");  
                password.setText("");  
  
            }  
              
        }  
    });  
    pan4.add(b_log);  
      
  
    JButton b_exit=new JButton("退出");  
    pan4.add(b_exit);   
    con.add(pan4);  
    //登陆和退出这两个按钮放在第四个版面上  
  
    JPanel pan5 = new JPanel();  
    con.add(pan5);  
    JPanel pan6 = new JPanel();  
    con.add(pan6);  
    JPanel pan7 = new JPanel();  
    con.add(pan7);  
    //空白版面  
  
}     
  
        }  
      
