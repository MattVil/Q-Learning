package tools;

public class BoundedCounter extends Counter{

	protected int min;
	protected int max;
	
	public BoundedCounter(int value, int min, int max){
		super(value);
		this.min=min;
		this.max=max;
	}
	
	public void increment(){
		if(counter < max)
			this.counter ++;
	}
	
	public void decrement(){
		if(counter > min){
			this.counter --;
		}
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.counter=this.min;
		this.max = max;
	}
	
	
}
