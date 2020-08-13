package design_patterns.structure_type.proxy.easy_proxy;

public class ProxyObject implements AbstractObject{

	RealObject realObject = new RealObject();
	@Override
	public void sellPhone() {
		System.out.println("环绕通知");
		realObject.sellPhone();
		System.out.println("环绕通知结束");
	}

}
