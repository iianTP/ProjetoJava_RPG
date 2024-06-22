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
	
	public void removeData(int itemIndex) {
		int i = 0;
		
		List slotBefore = null;
		List slot = this.first;
		List slotAfter = slot.next;
		
		while (i != itemIndex) {
			if (slotAfter == null) {
				break;
			}
			slotBefore = slot;
			slot = slot.next;
			slotAfter = slotAfter.next;
			i++;
		}
		
		if (slot != null) {
			if (slotBefore == null) {
				this.first = slotAfter;
			} else if (slotAfter == null) {
				this.last = slotBefore;
			} else {
				slotBefore.next = slotAfter;
			}
		}
		
	}
}