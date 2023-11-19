import java.text.DecimalFormat;

class symtab{
	int index;
	String name;
	int addr;
	
	symtab(int i, String s, int a){
		index = i;
		name = s;
		addr = a;
		
	
	}
}

class littab{
	int index;
	String name;
	int addr;
	
	littab(int i, String s, int a){
		index = i;
		name = s;
		addr = a;
	
	}
	void setaddr(int a){
		addr = a;
		}
}

public class a2{
	public static void main(String[] args){
		String ic[][]={{"(AD,01)",null,"(C,100)"}, {"(IS,04)","(RG,01)","(S,0)"}, {"(IS,01)","(RG,01)","(L,0)"},{"(IS,02)","(RG,01)","(S,2)"},{"(IS,01)","(RG,01)","(L,1)"},{"(DL,02)",null,"(C,2)"},{"(DL,02)",null,"(C,3)"},{"(IS,01)","(RG,01)","(RG,02)"},{"(IS,02)","(RG,01)","(RG,03)"},{"(IS,03)","(RG,01)","(L,2)"},{"(DL,02)",null,"(C,3)"},{"(DL,01)",null,"(C,1)"},{"(AD,02)",null,null}};
		System.out.println(ic);
		
	symtab s[] = new symtab[20];
	littab l[] = new littab[20];
	
	s[0] = new symtab(0,"A",110);
	s[1] = new symtab(1,"AGAIN",101);
	s[2] = new symtab(2,"B",109);
	s[3] = new symtab(3,"NEXT",107);
	s[4] = new symtab(4,"LOOP",110);
	
	l[0] = new littab(0,"='2'",104);
	l[1] = new littab(1,"='3'",105);
	l[2] = new littab(2,"='2'",111);
	
	int i = 0,j = 0, ind = 0;
	String m,op1,op2,temp;
	char arr1[],arr2[],arr3[];
	
	DecimalFormat df = new DecimalFormat("000");
	System.out.print("Machine code");
	while (i<ic.length){
		temp=null;
		arr1=null;
		arr2=null;
		arr3=null;
		
		m=ic[i][0];
		op1=ic[i][1];
		op2=ic[i][2];
		
		arr1 = m.toCharArray();
		if(op1!=null){
			arr2=op1.toCharArray();	
		}
		if(op2!=null){
			arr3=op2.toCharArray();	
		}
		if(arr1[1]=='I'&& arr1[2]=='S'){
			System.out.print(arr1[4]+""+arr1[5]+"\t");
			if(op1!=null){
				System.out.print(arr2[4]+""+arr2[5]+"\t");
			
			}else{
				System.out.print("00"+"\t");
			
			}
			if(op2!=null){
				if(arr3[1]=='R'&& arr3[2]=='G'){
				System.out.print(arr3[4]+arr3[5]+"\t");
			}
			else if(arr3[1]=='S'){
				ind=Character.getNumericValue(arr3[3]);
				j=4;
				while(arr3[j]!=')'){
					ind = ind*10;
					ind = ind +(Character.getNumericValue(arr3[j]));
					j++;
					}
				System.out.print(s[ind].addr+"\t");
			}else if(arr3[1]=='L'){
				ind = Character.getNumericValue(arr3[3]);
				j=4;
				while(arr3[j]!=')'){
					ind=ind*10;
					ind=ind+(Character.getNumericValue(arr3[j]));
					j++;
				
				}
				System.out.print(l[ind].addr+"\t");
				
			}
			}else{
				System.out.print("000"+"\t");
			}
		
		}else if(arr1[1]=='D' && arr1[2]=='L'){
			if(arr1[5]=='2'){
				System.out.print("00\t00\t");
				j=3;
				while(arr3[j]!=')'){
					if(temp==null)
						temp= String.valueOf(arr3[j]);
					else
						temp=temp.concat(String.valueOf(arr3[j]));
						j++;
					}
				System.out.print(df.format(Integer.parseInt(temp)));
				}
			}
		i++;
		System.out.print("\n");
		}
	}
}
