package com.wang.han;

import java.util.Scanner;

public class Tms{
	private Teacher[] teas=new Teacher[5];
	private int index;
	public void save(Teacher teacher){
		if(index>=teas.length){
			Teacher[] demo=new Teacher[teas.length+5];
			System.arraycopy(teas,0,demo,0,index);
			teas=demo;
		}
		teas[index++]=teacher;
	}


	public void update(Teacher teacher){
		for(int i=0;i<index;i++){
			if(teacher.getId()==teas[i].getId()){
				teas[i].setName(teacher.getName());
				teas[i].setCourse(teacher.getCourse());
				teas[i].setOffice(teacher.getOffice());
			}
		}	
	}


	public void deleteById(long id){
		int num=getIndexById(id);
		for(int i =num;i<index-1;i++){
			teas[i]=teas[i+1];
		}
		teas[--index]=null;
	}


	public Teacher[] queryAll(){
		Teacher[] demo = new Teacher[index];
		System.arraycopy(teas,0,demo,0,index);
		return demo;
	}


	public Teacher queryById(long id){
		int num = getIndexById(id);

		return num==-1?null:teas[num];
	}


	public Teacher queryByCourse(String course){
		int num = getIndexByCourse(course);
		return num==-1?null:teas[num];
	}


	private int getIndexById(long id){
		int num =-1;
		for (int i=0;i<index ;i++ ){
			if(teas[i].getId()==id){
				num=i;
				break;
			}
		}
		return num;
	}
	private int getIndexByCourse(String course){
		int num=-1;
		for (int i=0;i<index ;i++ ){
			if(teas[i].getCourse()==course){
				num=i;
				break;
			}
		}
		return num;
	}


	public void menu(){
		System.out.println("~~~~~~~~~~��ʦ����ϵͳ~~~~~~~~~~");
		System.out.println("1)��ѯ���н�ʦ��Ϣ");
		System.out.println("2)¼���ʦ��Ϣ");
		System.out.println("3)ͨ����ʦ���Ų��ҽ�ʦ��Ϣ");
		System.out.println("4)ͨ���γ̲�������Ӧ�Ľ�ʦ��Ϣ");
		System.out.println("5)ɾ����ʦ��Ϣ");
		System.out.println("6)�޸Ľ�ʦ��Ϣ");
		System.out.println("exit  �˳���ʦ����ϵͳ��");
		System.out.println("help ��ȡ����");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("�������Ӧָ�");
	}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Tms tms= new Tms();
		tms.menu();
		while(true){
			System.out.println("�������Ӧָ�");
			String option=sc.nextLine();
			switch(option){
				case "1":
					System.out.println("���������н�ʦ��Ϣ");
					Teacher[] teas=tms.queryAll();
					for(Teacher tea:teas){
						System.out.println(tea);
					}
					System.out.println("����ѯ��"+tms.index+"����ʦ��Ϣ");
					break;
				case "2":
					while(true){
					System.out.println("�밴���¸�ʽ���̹���#����#�γ�#�칫�ҡ������ʦ����Ϣ������break������һ��");
					String teaStr=sc.nextLine();
					if(teaStr.equals("break")){
						break;
					}
					String[] teaArr=teaStr.split("#");
					long id= Long.parseLong(teaArr[0]);
					String name=teaArr[1];
					String course=teaArr[2];
					int office=Integer.parseInt(teaArr[3]);
					Teacher tea=new Teacher(id,name,course,office);
					tms.save(tea);
					System.out.println("����ɹ�");
				}
					break;
				case "3":
					while(true){
					System.out.println("��������Ҫ���ҽ�ʦ�Ľ̹���id������break������һ��Ŀ¼");
					String idStr=sc.nextLine();
					if(idStr.equals("break")){
						break;
					}
					long id = Long.parseLong(idStr);
					Teacher tea=tms.queryById(id);
					System.out.println(tea==null?"�Բ���û�в鵽��Ҫ��ѯ�Ľ�ʦ":"��Ҫ��ѯ���Ľ�ʦ����ϢΪ:\n"+tea);
				}
					break;
				case "4"://case "4"���ڴ���
					while(true){
					System.out.println("��������Ҫ���ҽ�ʦ���̿γ̻�����break������һ��Ŀ¼");
					String courseStr=sc.nextLine();
					if(courseStr.equals("break")){
						break;
					}
					String course=courseStr;
					Teacher tea=tms.queryByCourse(course);
					System.out.println(tea==null?"�Բ���û�в鵽��Ҫ��ѯ�Ľ�ʦ":"��Ҫ��ѯ���Ľ�ʦ����ϢΪ:\n"+tea);
				}
					break;
				case "5":
					while(true){
					System.out.println("������Ҫɾ���Ľ�ʦ��id������break������һ��Ŀ¼");
					String idStr =sc.nextLine();
					if(idStr.equals("break")){
						break;
					}
					long id = Long.parseLong(idStr);
					Teacher tea=tms.queryById(id);
					if(tea==null){
						System.out.println("��Ҫɾ�����û�������");
						continue;
					}else{
						System.out.println("��Ҫɾ�����û���ϢΪ"+tea+"\n��ȷ��Ҫɾ����\nȷ��ɾ������Yes");
						String op=sc.nextLine();
						if(op.equals("Yes")){
							tms.deleteById(id);
							System.out.println("ɾ���ɹ�");
						}else{
							System.out.println("��������ɾ��ʧ��");
						}
					}	
				}
					break;
				case "6":
					while(true){
					System.out.println("������Ҫ�޸ĵĽ�ʦ��id������break������һ��");
					String idStr = sc.nextLine();
					if(idStr.equals("break")){
						break;
					}
					long id = Long.parseLong(idStr);
					Teacher tea=tms.queryById(id);
					if(tea==null){
						System.out.println("��Ҫ�޸ĵ��û������ڣ�");
						break;
					}
					System.out.println("ԭ��ϢΪ��"+tea);
					System.out.println("��������Ϣ������#�γ�#�칫�ҡ�");
					String str=sc.nextLine();
					String[] teaArr=str.split("#");
					String name=teaArr[0];
					String course=teaArr[1];
					int office=Integer.parseInt(teaArr[2]);
					Teacher newtea=new Teacher(id,name,course,office);
					tms.update(newtea);
					System.out.println("�޸ĳɹ�");
				}
					break;
				case "exit":
					System.out.println("��ӭ�´�ʹ��^_^");
					System.exit(0);
					break;
				case "help":
					tms.menu();
					break;
				default:
			}
		}
	}
}