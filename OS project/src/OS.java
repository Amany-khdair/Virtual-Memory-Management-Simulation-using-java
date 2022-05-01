import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/*import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;*/


public class OS extends javax.swing.JFrame{
	
	 static OS mainProgram;
	    final static int cyclesForDisk = 300;
	    final static int cyclesForMemoryAccess = 1;
	    int numberOfElementsInMemory = 0;
	    int pointer = 0;
	    Frame memory[];
	  //  ArrayList<Process> readyQueue = new ArrayList<Process>();       // ready queue to add arrived processes that are ready to run
	    int cycles = 0;
	    int MAX= 1000;
	    int tQuant = 4;
	    int prevProcess = 0;
	    int totalNumberOfFaults = 0;
	    String inputFilesPath;
	    public static  String N,M,S;
	    public static String[] data1;
	    public int m[]=new int [100];
	    public Thread  t1[] =new Thread[MAX];
		public Process obj[] = new Process[MAX];
		//public Process readyQueue[]=new Process[MAX];
		ArrayList<Process> readyQueue = new ArrayList<Process>(); 
		public int pageNumber[][] = new int[MAX][MAX];
		public Page p[]=new Page[MAX];
        public int references[]= { 7,0,1,2,0,3,0,4,2,3,0,3,0,3,2,1,2,0,1,7,0,1};
        public int ref[]= {1,2,3,4};
		public int fault=0; //LRU
		public int Fault=0; //FIFO
		public int a[]=new int[MAX];
		public int b[]=new int[MAX];
		public int v,h=0;
		
		
	   
	public OS() {
		initComponents();
		 		
	}
	
      
	private void initComponents()  {
		
	
		//ReadFile();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Virtual Memory Management Simulation");
		setBounds(100, 100, 755, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210, 230, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		 btnNewButton = new JButton("Delete Cells");//bring from file
		 btnNewButton.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 		 DefaultTableModel model = (DefaultTableModel) turnAroundTable.getModel();
		 	       if(e.getClickCount()==1) {
		 	    	//for (int k = 0; k <Integer.parseInt(N); k++) {
		 	    		// for(int u=0;u<6;u++) {
		 	    		
		 	    	//	 model.setValueAt(0, k, u);
		 	    	  if(turnAroundTable.getSelectedRow() != -1) 
		 	    		  model.removeRow(turnAroundTable.getSelectedRow());
		 	    	//	 }
		 		             //model.addRow(new Object[]{0,0,0,0,0,0});
		 		        	//obj[k].run();
		 		     //   }
		 		       
		 	           
		 		       
		 	       }
		 	}
		 });
		 btnNewButton.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		if(e.getStateChange()==ItemEvent.SELECTED) {
		 			  DefaultTableModel model = (DefaultTableModel) turnAroundTable.getModel();
		 		        for (int k = 0; k <Integer.parseInt(N); k++) {
		 		             model.addRow(new Object[]{0,0,0,0,0,0});
		 		        	//obj[k].run();
		 		         }
		 		        DefaultTableModel model1 = (DefaultTableModel) infoTable.getModel();
		 	            for (int k = 0; k < Integer.parseInt(N); k++) {
		 	                model1.addRow(new Object[]{0,0,0,0,0,0});
		 	            }
		 	           
		 		        System.out.println("Enter file name ");
		 				 Scanner sc=new Scanner(System.in);
		 				 String file=sc.nextLine();
		 				 File f=new File(file);
		 				 ReadFile(f);
		 		}
		 	}
		 });
		 System.out.println("Enter file name ");
		 Scanner sc=new Scanner(System.in);
		 String file=sc.nextLine();
		 File f=new File(file);
		 ReadFile(f);
			
		/*	btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnNewButton.setBounds(292, 15, 149, 38);
			contentPane.add(btnNewButton);
			btnNewButton.addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(java.awt.event.ActionEvent evt) {
		            	/* JFileChooser choose = new JFileChooser();
		     	        File selectedFile = new File("");
		     	        choose.setCurrentDirectory(selectedFile);
		     	        int returnValue = choose.showOpenDialog(null);
		     	        if (returnValue == JFileChooser.APPROVE_OPTION) {
		     	           
		     	            
		     	            selectedFile = choose.getSelectedFile();
		     	            
		     	            inputFilesPath = selectedFile.getParent();
		     	            
		     	            ReadFile(selectedFile);
		     	            btnNewButton.setEnabled(false);

		            }
		        });*/
		 btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(582, 17, 149, 38);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                //jButton1ActionPerformed(evt);
	            }
	        });

		
		
	    lblNewLabel = new JLabel("Memory Size");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(504, 86, 125, 51);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(490, 130, 139, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(M);
		
	   
		
	    lblNewLabel_1 = new JLabel("Time Quantum");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(496, 189, 133, 40);
		contentPane.add(lblNewLabel_1);
		
		textPane = new JTextPane();
		textPane.setBounds(490, 239, 139, 32);
		contentPane.add(textPane);
		textPane.setText(String.valueOf(tQuant));
		
		
		
	    lblNewLabel_2 = new JLabel("Pid");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 42, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("# of faults");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(65, 42, 78, 13);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("StartT");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(167, 42, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("FinshT");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(236, 42, 45, 13);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("TA");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(321, 42, 33, 13);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("WaitT");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setBounds(378, 42, 58, 13);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("RR Scheduling");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_8.setBounds(10, 10, 127, 22);
		contentPane.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("Memory View");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_9.setBounds(143, 281, 113, 21);
		contentPane.add(lblNewLabel_9);
		
		turnAroundTable = new javax.swing.JTable();

		turnAroundTable.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        turnAroundTable.setModel(new javax.swing.table.DefaultTableModel(
            new Thread [][] {

            },
            new String [] {
                "PID", "# of Faults", "Start Time", "Finish Time", "TurnAround", "Wait Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
       
        
        turnAroundTable.setRowHeight(32);
        turnAroundTable.setRowMargin(10);
       
        
        
        turnAroundTable.setBounds(10, 65, 431, 206);
		contentPane.add(turnAroundTable);
		
		infoTable = new javax.swing.JTable();
        infoTable.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        infoTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "PID", "start", "Duration(Burst time)", "size","Number of Pages"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false,false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            infoTable.setRowHeight(32);
            infoTable.setRowMargin(10);
           
            
            DefaultTableModel model1 = (DefaultTableModel) infoTable.getModel();
            for (int k = 0; k < Integer.parseInt(N); k++) {
            	
                        model1.addRow(new Object[]{obj[k].pid,String.valueOf(obj[k].start), String.valueOf(obj[k].dur), String.valueOf(obj[k].size),String.valueOf(m[k])});
                  	
               // model1.addRow(new Object[]{obj[k].pid,String.valueOf(obj[k].start), String.valueOf(obj[k].dur), String.valueOf(obj[k].size),String.valueOf(m[k])});
            }
           
          
            infoTable.setBounds(5, 419, 436, 134);
    		contentPane.add(infoTable);
    		
    		label = new JLabel("Pid");
    		label.setFont(new Font("Tahoma", Font.BOLD, 14));
    		label.setBounds(10, 387, 52, 22);
    		contentPane.add(label);
    		
    		label_1 = new JLabel("StartT");
    		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
    		label_1.setBounds(95, 391, 56, 18);
    		contentPane.add(label_1);
    		
    		lblNewLabel_10 = new JLabel("Duration");
    		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 14));
    		lblNewLabel_10.setBounds(178, 390, 64, 19);
    		contentPane.add(lblNewLabel_10);
    		
    		lblNewLabel_11 = new JLabel("# of pages");
    		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 14));
    		lblNewLabel_11.setBounds(352, 387, 100, 22);
    		contentPane.add(lblNewLabel_11);
    		
    		lblNewLabel_12 = new JLabel("Size");
    		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 14));
    		lblNewLabel_12.setBounds(286, 393, 58, 16);
    		contentPane.add(lblNewLabel_12);
    		
    		comboBox = new JComboBox();
    		comboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
    		comboBox.addItemListener(new ItemListener() {
    			public void itemStateChanged(ItemEvent e) {
    				if(e.getStateChange()==ItemEvent.SELECTED) {
    					if(comboBox.getSelectedItem().toString().equals("LRU")) {
    						ReentrantLock lock =new ReentrantLock(); 
    						 DefaultTableModel model = (DefaultTableModel) turnAroundTable.getModel();
    						
    	                     
    	                        	    for (int k = 0; k <Integer.parseInt(N); k++) {
    	                        	    	
    	      					             model.addRow(new Object[]{obj[k].pid,b[k],String.valueOf(obj[k].start),String.valueOf(obj[k].finishTime),String.valueOf(obj[k].turnaround),String.valueOf(obj[k].waitTime)});
    	                        	    } 
    	                        	  //t1[j].run();
    	                        	    textField_2.setText(String.valueOf(h));
    	                        	    textField_1.setText(String.valueOf(cycles));
    	                          }
    					
    					}
    					    
    					       

    					
    					else if(comboBox.getSelectedItem().toString().equals("FIFO")) {
    						 DefaultTableModel model = (DefaultTableModel) turnAroundTable.getModel();
 					        for (int k = 0; k <Integer.parseInt(N); k++) {
 					             model.addRow(new Object[]{obj[k].pid,a[k],String.valueOf(obj[k].start),String.valueOf(obj[k].finishTime),String.valueOf(obj[k].turnaround),String.valueOf(obj[k].waitTime)});
 					        	//obj[k].run();
 					         }
 					       textField_2.setText(String.valueOf(v));
 					      textField_1.setText(String.valueOf(cycles));
    					}
    				}
    			
		});
    		comboBox.setBounds(490, 361, 199, 32);
    		contentPane.add(comboBox);
    		comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose","FIFO", "LRU" }));
    		comboBox.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                	
                      
                }
    			 
            });


    		
    		lblNewLabel_13 = new JLabel("Total Cycle ");
    		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
    		lblNewLabel_13.setBounds(467, 423, 133, 33);
    		contentPane.add(lblNewLabel_13);
    		
    		lblNewLabel_14 = new JLabel("Total # of PageFaults");
    		lblNewLabel_14.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
    		lblNewLabel_14.setBounds(467, 476, 172, 29);
    		contentPane.add(lblNewLabel_14);
    		
    		textField_1 = new JTextField();
    		textField_1.setBounds(635, 427, 96, 29);
    		contentPane.add(textField_1);
    		textField_1.setColumns(10);
    		//textField_1.setText(String.valueOf(cycles));
    		
    		textField_2 = new JTextField();
    		textField_2.setBounds(635, 478, 96, 29);
    		contentPane.add(textField_2);
    		textField_2.setColumns(10);
    		
    		
    		
    		JLabel lblNewLabel_15 = new JLabel("Choose Algorithm");
    		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
    		lblNewLabel_15.setBounds(490, 323, 139, 28);
    		contentPane.add(lblNewLabel_15);
    		
    		JTextArea textArea = new JTextArea();
    		textArea.setBounds(311, 323, -281, 22);
    		contentPane.add(textArea);
    		
    		txtCheck = new JTextField();
    		txtCheck.setHorizontalAlignment(SwingConstants.CENTER);
    		txtCheck.setForeground(Color.RED);
    		txtCheck.setFont(new Font("Tahoma", Font.BOLD, 15));
    		txtCheck.setText("CHECK CONSOLE !");
    		txtCheck.setBounds(10, 312, 431, 33);
    		contentPane.add(txtCheck);
    		txtCheck.setColumns(10);
    		
    		JLabel lblNewLabel_16 = new JLabel("Process Info");
    		lblNewLabel_16.setFont(new Font("Tahoma", Font.BOLD, 15));
    		lblNewLabel_16.setBounds(10, 355, 100, 22);
    		contentPane.add(lblNewLabel_16);
    		
    		JScrollBar scrollBar = new JScrollBar();
    		scrollBar.setBounds(439, 65, 17, 206);
    		contentPane.add(scrollBar);
    		       
    	//	scrollBar.add(turnAroundTable);
    		
    		JScrollBar scrollBar_1 = new JScrollBar();
    		scrollBar_1.setBounds(439, 419, 17, 134);
    		contentPane.add(scrollBar_1);
    		
    		
		
				
	 }
	
 

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OS frame = new OS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	   
	}
	
	
public void RR() {
	Process p=new Process();
	 
		memory = new Frame[Integer.parseInt(M)];
		 for (int i = 0; i < Integer.parseInt(M); i++) // initialize memory
	        {
	            memory[i] = new Frame();
	           
	           
	        }
		 int i = 0;
		 int t=0;
		 
		   int time = 0;// current time
	        while (!allProcessesFinished()) {
	        	 
	            prevProcess = i;
	            checkProcessesArrival(time);    // check if any process has arriced at current moment 
	            
	            if (readyQueue.size() == 0) {
	                time++;
	                continue;
	            }
	           
	            i = i % readyQueue.size();
	          

	           
	            p= readyQueue.get(i++);
	            

	       
	           
	            if (prevProcess != i) {    //context switching cost
	                cycles += 5;
	            }
	            
	     
	            if (p.startTime==-1) {
	                p.startTime = time;
	            }
	           
                
	            for (int j = p.pageLocation; j < tQuant + p.pageLocation; j++) {

	            
	            
	           
	               PageReplacment(p.page[j]);
	          
	               printMemory();
	               checkProcessesArrival(++time);    // check if any process has arriced at current moment 
	               p.remainingTime--;
	                if (p.remainingTime == 0) {
	                    break;
	                }
	            }
	            p.pageLocation += tQuant;

	            if (p.remainingTime == 0) {
	            	p.finishTime = time;
	                readyQueue.remove(p);
	                
	            	p.turnaround = p.finishTime -p.start;
	            	p.waitTime = p.turnaround - p.dur;
	            	
	            }
	         
	
            	
	        }
	        
	        //Thread t1=new Thread(p);
	       // t1.start();
 
}

public void printMemory() {
	 
	File file=new File("veiwmemory.txt");
	int r=0;
    String result = "";
    try {
    	
    	FileWriter fileWriter = new FileWriter(file, true);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    for (int i = 0; i < Integer.parseInt(M); i++) {
        if (memory[i].page != null) {
            if (memory[i].bitReference) {
                result += "\n"     +"TRUE"             ;
            } else {
                result += "\n"    +"FALSE"  ;
            }
            
          //  System.out.println(memory[i].page.processPid);
           
           if (i != pointer) {
        	  
                result += " ==> ( process: " + memory[i].page.processPid +") \n";
                
        	  
            } else {
            	  
                result += " ==> ( process: " + memory[i].page.processPid +") \n";
               
            	  
            }
           
        } else {
            result += "\n  --\n";
        }
      
       
    }
  
    result += "\n*****************************";
    
    bufferedWriter.append(result);
     
        System.out.println(result);
     }
     catch (IOException e) {
   
     }
    
   
    System.out.println(result);
    System.out.println("----------------------------------------------");
  
}

  public void PageReplacment(Page pg) {
	  
	 
	  int indexOfPageInMemory;
      indexOfPageInMemory = idxInMemory(pg);
	 
      if (numberOfElementsInMemory < Integer.parseInt(M)) {
          if (indexOfPageInMemory != -1) {       // Process p is in memory
              memory[indexOfPageInMemory].bitReference = true;
              cycles += cyclesForMemoryAccess;
          } else {
              memory[pointer].page = pg;
              pointer = (pointer + 1) % Integer.parseInt(M);
              numberOfElementsInMemory++;
             
              cycles += cyclesForDisk;
             
             
          }
       
      }
      
      else if (indexOfPageInMemory != -1) {        // Process p is in memory
          memory[indexOfPageInMemory].bitReference = true;
          cycles += cyclesForMemoryAccess;
      } 
      else {
          if (memory[pointer].bitReference) {
              pointer = resetBitReference(pointer);
          }
          memory[pointer].page = pg;
          pointer = (pointer + 1) %  Integer.parseInt(M);
          cycles += cyclesForDisk;
        
          
      }
     
  }
    public boolean equalPages(Page pg1, Page pg2) {
      if (pg1 == null || pg2 == null) {
          return false;
      }
      if ((pg1.processPid == pg2.processPid)) {
          return true;
      }

     return false;
  }
 
    public int idxInMemory(Page pg) {
      for (int i = 0; i < Integer.parseInt(M); i++) {
          if (equalPages(pg, memory[i].page)) {
              return i;
          }
      }
      return -1;
  }
  
  public int resetBitReference(int pointer) {
      for (int i = pointer; i < Integer.parseInt(M); i = (i + 1) % Integer.parseInt(M)) {
          if (memory[i].bitReference) {
              memory[i].bitReference = false;
              pointer = (pointer + 1) % Integer.parseInt(M);
          } else {
              break;
          }
      }
      return pointer;
  }
  
   public int  FIFO(int frames,int reference []) {
	 
	   int ref_len=reference.length;
	   int buffer[] = new int[frames];  
	   int Hit=0;
       
       int j=0;
       boolean check;
       for(int i=0;i<frames;i++)
    	   buffer[i]=-1;
       
       for(int i=0;i<ref_len;i++) {
    	   check=false;
    	   
    	   for(int k=0;k<frames;k++)
               if(buffer[k]==reference[i])
               {
                       check=true;
                       Hit=Hit+1;
               }
               if(check==false)
               {
            	   buffer[j]=reference[i];
                       j++;
                       if(j>=frames)
                       j=0;
                       Fault=Fault+1;
               }
        
       }
              
     //  System.out.println("FAULT: "+Fault);
       return Fault;
	
   }
	

	public  int  LRU(int frames,int reference []) {
		int pointer = 0, hit = 0, fault = 0,ref_len=reference.length; //frames=# of frames(memory trace)  //red_len=size of the pafe numbers array
        Boolean isFull = false;
        int buffer[];
   
        int mem_layout[][];
        ArrayList<Integer> stack = new ArrayList<Integer>();
        mem_layout = new int[ref_len][frames];
        buffer = new int[frames];
        
        
        //initilaize 
        for(int j = 0; j < frames; j++)
            buffer[j] = -1;
        
        //*******************
        for(int i = 0; i < ref_len; i++)
        {
        	 if(stack.contains(reference[i]))
             {
              stack.remove(stack.indexOf(reference[i]));
             }
             stack.add(reference[i]);
             int search = -1;
             for(int j = 0; j < frames; j++)
             {
                 if(buffer[j] == reference[i])
                 {
                     search = j;
                     hit++;
                     break;
                 }
             }
             if(search == -1)
             {
              if(isFull)
              {
               int min_loc = ref_len;
                     for(int j = 0; j < frames; j++)
                     {
                      if(stack.contains(buffer[j]))
                         {
                             int temp = stack.indexOf(buffer[j]);
                             if(temp < min_loc)
                             {
                                 min_loc = temp;
                                 pointer = j;
                             }
                         }
                     }
              }
                 buffer[pointer] = reference[i];
                 fault++;
                 pointer++;
                 if(pointer == frames)
                 {
                  pointer = 0;
                  isFull = true;
                 }
             }
             for(int j = 0; j < frames; j++)
                 mem_layout[i][j] = buffer[j];
         }
        
      /*  for(int i = 0; i < frames; i++)
        {
            for(int j = 0; j < ref_len; j++)
                System.out.printf("%3d ",mem_layout[j][i]);
            System.out.println();
        }*/
        
        //System.out.println("The number of Faults: " + fault);
        return fault;
    }
       
       
       
	
	public boolean allProcessesFinished() {  
       // for (Process p : obj) {
        	for(int j=0;j<Integer.parseInt(N);j++) {
            if (obj[j].finishTime == -1) //if equal -1 then not finished
            {
                return false;
            }
        }
        return true;
    }
	
	  public void checkProcessesArrival(int time) {
		
	         
	              for(int i=0;i<Integer.parseInt(N);i++) {
	            if (obj[i].arrivalTime == time && !readyQueue.contains(obj[i])) 
	            {
	                readyQueue.add(obj[i]);     
	            }
	         // readyQueue[i].run();
	        }
	    }
	
	public void ReadFile(File f){
		System.out.println("Page Numbers");
       
		try {
          
            Scanner myReader = new Scanner(f);
            N=myReader.nextLine();
            M=myReader.nextLine();
            S=myReader.nextLine();
            String delim=" ";
            int count=0;
            int j=0;
            int check=0;
           
         
         
            double pageSize=Math.pow(2, 12);
         

            String data=null;
        
            while (j < Integer.parseInt(N))  {
                data = myReader.nextLine();
                data1=data.split(delim);
                for(int i= 4 ; i <data1.length ;i++ ) {
                	count++;            	                 	
                }
                if(count < Integer.parseInt(S) ) {
                   
                     	obj[j]= new Process("ERROR! the frames less than the minmum # of frams in proccess with pid \"+data1[0])",0,0,0);
                     	t1[j]= new Thread(obj[j]);
                     	
                     }
                     else if(count > Integer.parseInt(data1[3])){
                     	System.out.println("ERROR! the frames larger than the size of the memory in proccess with pid "+data1[0]);
                     }else {
                          obj[j] = new Process(data1[0], Double.parseDouble(data1[1]), Double.parseDouble(data1[2]), Integer.parseInt(data1[3]));       
                          t1[j]= new Thread(obj[j]);
                          for(int i = 0 ;i < count ;i++) {
                          	pageNumber[i][j]=(int) (Integer.parseInt(data1[i+4], 16) / Math.pow(2, 12));
                          	p[j]=new Page(Integer.parseInt(data1[0]),pageNumber[i][j]);
                          	obj[j].page[j]=p[j];  
                          	System.out.println(obj[j].page[j].pageNumber);
                          }
                         
                          System.out.print("\n");
                   /*   try{  
                          synchronized(t1[j]) {
                        	  t1[j].run();
                        	  t1[j].sleep(5000);
                          }}catch(InterruptedException ex) {
                        	  
                        	  Logger.getLogger(OS.class.getName()).log(Level.SEVERE,null,ex);
                          }*/
                          
                     }
                
                m[j]=count;
                
        
               // p[j]=new Page(Integer.parseInt(data1[0]));
              //  obj[j].page[j]=p[j];
             
                
              
               count=0;
                j++;    
            }
          
           RR();
      for(int k=0;k<Integer.parseInt(N);k++) {
              a[k]=FIFO(m[k],ref);
              Fault=0;
              v+=a[k];
              b[k]=LRU(m[k],references);
              fault=0;
              h+=b[k];
                 
           }
          
         
     
          
         myReader.close();
          
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
       
    }
	
	   private void browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseActionPerformed
	        JFileChooser choose = new JFileChooser();
	        File selectedFile = new File("");
	        choose.setCurrentDirectory(selectedFile);
	        int returnValue = choose.showOpenDialog(null);
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
	           
	            
	            selectedFile = choose.getSelectedFile();
	            
	            inputFilesPath = selectedFile.getParent();
	            
	            ReadFile(selectedFile);
	            btnNewButton.setEnabled(false);
	        }
	    }//GEN-LAST:event_browseActionPerformed
	   
	   /*private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
	        mainProgram.dispose();
	        mainProgram = new OS();
	        mainProgram.setVisible(true);
		   DefaultTableModel model = (DefaultTableModel) turnAroundTable.getModel();
	        for (int k = 0; k <Integer.parseInt(N); k++) {
	             model.addRow(new Object[]{0,0,0,0,0,0});
	        	//obj[k].run();
	         }
	        DefaultTableModel model1 = (DefaultTableModel) infoTable.getModel();
            for (int k = 0; k < Integer.parseInt(N); k++) {
                model1.addRow(new Object[]{0,0,0,0,0,0});
            }
           
	        System.out.println("Enter file name ");
			 Scanner sc=new Scanner(System.in);
			 String file=sc.nextLine();
			 File f=new File(file);
			 ReadFile(f);
	    }//GEN-LAST:event_jButton1ActionPerformed/*/
      
	

	
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel ;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;
	private JTextPane textPane;//quanum
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private javax.swing.JTable turnAroundTable;
	private javax.swing.JTable infoTable;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JComboBox comboBox;
	private JLabel lblNewLabel_13;
	private JLabel lblNewLabel_14;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField txtCheck;
}