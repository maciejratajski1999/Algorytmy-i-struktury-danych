package lista6

sealed class Expr (val presentation:String){
    class Const (val x:Double):Expr("$x")
    class Plus (val a:Expr, val b:Expr):Expr("($a)+($b)")
    class Min (val a:Expr, val b:Expr):Expr("($a)-($b)")
    class Neg (val a:Expr):Expr("-($a)")
    class Var (val v:String):Expr("$v")
    class Mul (val a:Expr, val b:Expr):Expr("($a)*($b)")
    class Div (val a:Expr, val b:Expr):Expr("($a)/($b)")
    class Log (val a:Expr):Expr("ln($a)")
    class Pow (val a:Expr, val b:Expr):Expr("($a)^($b)")

    override fun toString(): String = presentation
}

fun der(a:Expr, x:String):Expr = when(a){
    is Expr.Const -> Expr.Const(0.0)
    is Expr.Plus -> Expr.Plus(der(a.a, x),der(a.b, x))
    is Expr.Min -> Expr.Min(der(a.a, x),der(a.b, x))
    is Expr.Neg -> Expr.Mul(Expr.Const(-1.0), der(a,x))
    is Expr.Var -> if (a.v==x){Expr.Const(1.0)} else{Expr.Const(0.0)}
    is Expr.Mul -> Expr.Plus(Expr.Mul(der(a.a,x),a.b), Expr.Mul(a.a, der(a.b,x)))
    is Expr.Div -> Expr.Div(Expr.Min(Expr.Mul(der(a.a, x),a.b), Expr.Mul(a.a, der(a.b,x))), Expr.Mul(a.b,a.b))
    is Expr.Log -> Expr.Div(der(a.a,x),a.a)
    is Expr.Pow -> Expr.Mul(Expr.Pow(a.a, a.b),Expr.Plus(Expr.Mul(der(a.a,x), Expr.Div(a.b,a.a)), Expr.Mul(der(a.b,x), Expr.Log(a.a))))
}

fun main(){
    val fx = Expr.Pow(Expr.Const(2.0),Expr.Var("x"))
    println(der(fx, "x").toString())
}

// finished B)