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
		System.out.println("~~~~~~~~~~教师管理系统~~~~~~~~~~");
		System.out.println("1)查询所有教师信息");
		System.out.println("2)录入教师信息");
		System.out.println("3)通过教师工号查找教师信息");
		System.out.println("4)通过课程查找所对应的教师信息");
		System.out.println("5)删除教师信息");
		System.out.println("6)修改教师信息");
		System.out.println("exit  退出教师管理系统！");
		System.out.println("help 获取帮助");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("请输入对应指令：");
	}
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		Tms tms= new Tms();
		tms.menu();
		while(true){
			System.out.println("请输入对应指令：");
			String option=sc.nextLine();
			switch(option){
				case "1":
					System.out.println("以下是所有教师信息");
					Teacher[] teas=tms.queryAll();
					for(Teacher tea:teas){
						System.out.println(tea);
					}
					System.out.println("共查询到"+tms.index+"个教师信息");
					break;
				case "2":
					while(true){
					System.out.println("请按如下格式【教工号#姓名#课程#办公室】输入教师的信息或输入break返回上一级");
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
					System.out.println("保存成功");
				}
					break;
				case "3":
					while(true){
					System.out.println("请输入您要查找教师的教工号id或输入break返回上一级目录");
					String idStr=sc.nextLine();
					if(idStr.equals("break")){
						break;
					}
					long id = Long.parseLong(idStr);
					Teacher tea=tms.queryById(id);
					System.out.println(tea==null?"对不起，没有查到你要查询的教师":"您要查询到的教师的信息为:\n"+tea);
				}
					break;
				case "4"://case "4"存在错误；
					while(true){
					System.out.println("请输入您要查找教师所教课程或输入break返回上一级目录");
					String courseStr=sc.nextLine();
					if(courseStr.equals("break")){
						break;
					}
					String course=courseStr;
					Teacher tea=tms.queryByCourse(course);
					System.out.println(tea==null?"对不起，没有查到你要查询的教师":"您要查询到的教师的信息为:\n"+tea);
				}
					break;
				case "5":
					while(true){
					System.out.println("输入您要删除的教师的id或输入break返回上一级目录");
					String idStr =sc.nextLine();
					if(idStr.equals("break")){
						break;
					}
					long id = Long.parseLong(idStr);
					Teacher tea=tms.queryById(id);
					if(tea==null){
						System.out.println("您要删除的用户不存在");
						continue;
					}else{
						System.out.println("您要删除的用户信息为"+tea+"\n您确定要删除吗？\n确定删除输入Yes");
						String op=sc.nextLine();
						if(op.equals("Yes")){
							tms.deleteById(id);
							System.out.println("删除成功");
						}else{
							System.out.println("输入有误，删除失败");
						}
					}	
				}
					break;
				case "6":
					while(true){
					System.out.println("输入您要修改的教师的id或输入break返回上一级");
					String idStr = sc.nextLine();
					if(idStr.equals("break")){
						break;
					}
					long id = Long.parseLong(idStr);
					Teacher tea=tms.queryById(id);
					if(tea==null){
						System.out.println("您要修改的用户不存在！");
						break;
					}
					System.out.println("原信息为："+tea);
					System.out.println("输入新信息【姓名#课程#办公室】");
					String str=sc.nextLine();
					String[] teaArr=str.split("#");
					String name=teaArr[0];
					String course=teaArr[1];
					int office=Integer.parseInt(teaArr[2]);
					Teacher newtea=new Teacher(id,name,course,office);
					tms.update(newtea);
					System.out.println("修改成功");
				}
					break;
				case "exit":
					System.out.println("欢迎下次使用^_^");
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