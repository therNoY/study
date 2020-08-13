package design_patterns.structure_type.proxy.easy_proxy;

public class RealObject implements AbstractObject {

	@Override
	public void sellPhone() {
		System.out.println("销售手机");
	}

}
