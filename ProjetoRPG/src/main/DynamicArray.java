package main;

public abstract class DynamicArray<T> {

	private class List {
		T data;
		List next;
		public List(T data) {
			this.data = data;
		}
	}
	
	private List first;
	private List last;

	public void addData(T data) {
		List newSlot = new List(data);
		if (this.last == null) {
			this.first = last = newSlot;
		} else {
			this.last.next = newSlot;
			this.last = newSlot;
		}
	}
	
	public T getData(int itemIndex) {
		int i = 0;
		List slot = this.first;
		while (i != itemIndex) {
			if (slot == null) {
				break;
			}
			slot = slot.next;
			i++;
		}
		return (slot != null) ? slot.data : null;
	}
}