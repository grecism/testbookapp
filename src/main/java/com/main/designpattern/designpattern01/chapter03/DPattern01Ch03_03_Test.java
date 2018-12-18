package com.main.designpattern.designpattern01.chapter03;

/**
 * 
 *<p>Title	: DPattern01Ch03_03_Test</p>
 * @Description	:
 * @author	: admin
 * @date	: 2017年12月27日下午2:58:19
 */
public class DPattern01Ch03_03_Test {
	/**3.3解释器模式**/
	//解释器模式（Interpreter Pattern）提供了评估语言的语法或表达式的方式，它属于行为型模式。这种模式实现了一个表达式接口，该接口解释一个特定的上下文。这种模式被用在 SQL 解析、符号处理引擎等。
	//优点： 1、可扩展性比较好，灵活。 2、增加了新的解释表达式的方式。 3、易于实现简单文法。
	//缺点： 1、可利用场景比较少。 2、对于复杂的文法比较难维护。 3、解释器模式会引起类膨胀。 4、解释器模式采用递归调用方法。
	public static void main(String[] args) {
		//3.InterpreterPatternDemo使用Expression来创建规则，并解析他们。
		DPattern01Ch03_03_Expression isMale = getMaleExpression();
		DPattern01Ch03_03_Expression isMarriedWoman = getMarriedWomanExpression();
		System.out.println("John is Male ?"+isMale.interpret("John"));
		System.out.println("Julie is a married woman ?"+isMarriedWoman.interpret("Married Julie"));
	}

	//规则：Robert 和 John 是男性
	public static DPattern01Ch03_03_Expression getMaleExpression(){
		DPattern01Ch03_03_Expression robert = new DPattern01Ch03_03_TerminaExpression("Robert");
		DPattern01Ch03_03_Expression john = new DPattern01Ch03_03_TerminaExpression("John");
		return new DPattern01Ch03_03_OrExpression(robert,john);
	}
	//规则：Julie 是一个已婚的女性
	public static DPattern01Ch03_03_Expression getMarriedWomanExpression(){
		DPattern01Ch03_03_Expression julie  = new DPattern01Ch03_03_TerminaExpression("Julie");
		DPattern01Ch03_03_Expression married  = new DPattern01Ch03_03_TerminaExpression("Married");
		return new DPattern01Ch03_03_AndExprssion(julie,married);
	}
}

//1.创建一个表达式接口。
interface DPattern01Ch03_03_Expression{
	public boolean interpret(String context);
}

//2.创建实现了上述接口的实体类。
class DPattern01Ch03_03_TerminaExpression implements DPattern01Ch03_03_Expression{
	private String data;
	public DPattern01Ch03_03_TerminaExpression(String data) {
		this.data = data;
	}
	@Override
	public boolean interpret(String context) {
		if(context.contains(data)){
			return true;
		}
		return false;
	}
}

class DPattern01Ch03_03_OrExpression implements DPattern01Ch03_03_Expression{
	private DPattern01Ch03_03_Expression expr1 = null;
	private DPattern01Ch03_03_Expression expr2 = null;
	public DPattern01Ch03_03_OrExpression(DPattern01Ch03_03_Expression expr1, DPattern01Ch03_03_Expression expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	@Override
	public boolean interpret(String context) {
		return expr1.interpret(context) || expr2.interpret(context);
	}
}

class DPattern01Ch03_03_AndExprssion implements DPattern01Ch03_03_Expression{
	private DPattern01Ch03_03_Expression expr1 = null;
	private DPattern01Ch03_03_Expression expr2 = null;
	public DPattern01Ch03_03_AndExprssion(DPattern01Ch03_03_Expression expr1, DPattern01Ch03_03_Expression expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	@Override
	public boolean interpret(String context) {
		return expr1.interpret(context) && expr2.interpret(context);
	}
}














