package others;

public class TestowanieNieStatic {
	public int a = 2;
	
	TestowanieNieStatic(){
		
		System.out.println(a);
	}
	TestowanieNieStatic(int a){
		this.a = a;
		System.out.println(a);
	}
	
	int f() {
		
		return a+3;
	}
}
