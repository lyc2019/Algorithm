public class QuickSort {

	public static void myQuickSort(int[] arr, int l, int r) {

		if (l < r) {
			
			int i = l;
			int j = r;
			int x = arr[l];
			while (i < j) {

				while (i < j && arr[j] >= x) {

					j--;
				}

				if (i < j) {
					
					arr[i++] = arr[j];
				}

				while (i < j && arr[i] < x) {

					i++;
				}

				if (i < j) {
					
					arr[j--] = arr[i];
				}
			}
			arr[i] = x;
			myQuickSort(arr, l, i - 1);
			myQuickSort(arr, i + 1, r);
		}
	}

	public static void printAll(int[] arr) {

		for (int value : arr) {

			System.out.print(value+" ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		
		int[] arr = { 3, 4, 1, 6, 5 };
		printAll(arr);
		myQuickSort(arr, 0, 4);
		printAll(arr);
	}
}







