import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MinIntHeap<T extends Comparable<T>> {
	private int _capacity = 10;
	private int _size = 0;
	private int _revert = 1; // -1: not reverted
	@SuppressWarnings("rawtypes")
	// size of _items will change when ensureCapacity is called and doubles cap
	private Comparable[] _items = new Comparable[_capacity];

	MinIntHeap() {
	}

	MinIntHeap(boolean setReverted) {
		_revert = setReverted ? 1 : -1;
	}

	// *********** helper functions ************************
	// can be sorted as an array, not a tree
	// start from 0
	// assuming tree is complete (that's what a heap is).
	// exp: left child of 1 is 3, left child of 3 is 7...
	private int getLeftChildIndex(int parentIndex) {
		return 2 * parentIndex + 1;
	}

	// right child of 1 is 4, right child of 6 is 14....
	private int getRightChildIndex(int parentIndex) {
		return 2 * parentIndex + 2;
	}

	// exp: 13 & 14 have parent = 6
	private int getParentIndex(int childIndex) {
		return (childIndex - 1) / 2;
	}

	private boolean hasLeftChild(int index) {
		return getLeftChildIndex(index) < _size;
	}

	private boolean hasRightChild(int index) {
		return getRightChildIndex(index) < _size;
	}

	private boolean hasParent(int index) {
		return getParentIndex(index) >= 0;
	}

	@SuppressWarnings({ "rawtypes" })
	private Comparable leftChild(int index) {
		return _items[getLeftChildIndex(index)];
	}

	@SuppressWarnings({ "rawtypes" })
	private Comparable rightChild(int index) {
		return _items[getRightChildIndex(index)];
	}

	@SuppressWarnings({ "rawtypes" })
	private Comparable parent(int index) {
		return _items[getParentIndex(index)];
	}

	private void swap(int indexOne, int indexTwo) {
		@SuppressWarnings("rawtypes")
		Comparable temp = _items[indexOne];
		_items[indexOne] = _items[indexTwo];
		_items[indexTwo] = temp;
	}

	private void ensureExtraCapcity() {
		if (_size == _capacity) {
			_capacity *= 2;
			_items = Arrays.copyOf(_items, _capacity);
		}
	}

	// return first elem of tree (or array)
	@SuppressWarnings("unchecked")
	public T peek() {
		if (_size == 0)
			return null;
		return (T) _items[0];
	}

	// removes and return first elem of tree
	@SuppressWarnings("unchecked")
	public T poll() {
		if (_size == 0)
			return null;
		T item = (T) _items[0];
		// bottom-right elem becomes first
		_items[0] = _items[_size - 1];
		_size--;
		// bubble down elem
		heapifyDown();
		return item;
	}

	// adds elem in tree
	public void add(T item) {
		ensureExtraCapcity();
		// add in bottom left
		_items[_size] = item;
		_size++;
		// bubble up element
		heapifyUp();
	}

	public int getSize() {
		return _size;
	}

	@SuppressWarnings("rawtypes")
	public Comparable[] getContentCopy() {
		return Arrays.copyOf(_items, _size);
	}

	// check from ze top
	// swaps the elem down with the smallest child when it is smaller than the
	// smallest.
	// repeat until it is bigger than the smallest child
	@SuppressWarnings("unchecked")
	private void heapifyDown() {
		int index = 0;
		// at least one child exists
		while (hasLeftChild(index)) {
			// guess which one is smaller, left or right?
			int smallerChildIndex = getLeftChildIndex(index);
			if (hasRightChild(index) && _revert * (rightChild(index).compareTo(leftChild(index))) < 0) {
				smallerChildIndex = getRightChildIndex(index);
			}

			// check if elem needs to be bubbled down (is lower than the smallest
			// child)
			if (_revert * (_items[index].compareTo(_items[smallerChildIndex])) < 0) {
				break;
			} else { // elem is bigger than smallest child, needs to swap
				swap(index, smallerChildIndex);
			}
			// child pos is now the new pos of the element
			index = smallerChildIndex;
		}

	}

	// starts with bottom right
	// swaps with parent whenever parent > elem
	@SuppressWarnings("unchecked")
	private void heapifyUp() {
		int index = _size - 1;
		// note: no need to check left child we already have leftChild < parent thus
		// , since parent < rightChild,
		// we also have lefParent < rightChild
		while (hasParent(index) && _revert * (parent(index).compareTo(_items[index])) > 0) {
			swap(getParentIndex(index), index);
			index = getParentIndex(index);
		}

	}

}

// **************** TESTING *******************************
class testHeap {
	static public void main(String[] args) {
		System.err.println("hey dudes");

		MinIntHeap<Integer> zeHeap = new MinIntHeap<Integer>(); // heap to
		// test

		// fill with nbs
		ArrayList<Integer> someList = new ArrayList<Integer>();
		for (int i = 1; i <= 30; i++) {
			someList.add(Integer.valueOf(i));

		}
		Collections.shuffle(someList);

		for (Integer elem : someList) {
			System.err.format("adding %d \n", elem);
			zeHeap.add(elem);
		}

		System.err.println("heap contents : " + Arrays.toString(zeHeap.getContentCopy()));

		// take out 6 lowest elements,check heapify up works
		for (int i = 0; i < 6; i++) {
			System.err.println("removing " + zeHeap.poll());
		}

	}
}
