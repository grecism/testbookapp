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
	//实现:
	//我们将创建一个接口 Expression 和实现了 Expression 接口的实体类。定义作为上下文中主要解释器的 TerminalExpression 类。其他的类 OrExpression、AndExpression 用于创建组合式表达式。
	//InterpreterPatternDemo，我们的演示类使用 Expression 类创建规则和演示表达式的解析。
	public static void main(String[] args) {
		
	}
}




















