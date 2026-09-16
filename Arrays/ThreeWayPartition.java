class ThreeWayPartition {

    public static void threeWaySort(int[] arr) {
        int pivot = arr[arr.length / 2];
        int lo = 0, mid = 0, hi = arr.length - 1;

        while (mid <= hi) {
            if (arr[mid] < pivot) {
                swap(arr, lo++, mid++);
            } else if (arr[mid] == pivot) {
                mid++;
            } else {
                swap(arr, mid, hi--);
            }
        }
    }

    // Quickselect with 3-way partition — handles duplicates in O(n) best case
    public static int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private static int quickSelect(int[] nums, int left, int right, int target) {
        if (left == right) return nums[left];

        int randIdx = left + (int) (Math.random() * (right - left + 1));
        swap(nums, randIdx, right);
        int pivot = nums[right];

        // 3-way partition: returns [lo, hi] where nums[lo..hi] == pivot
        int lo = left, mid = left, hi = right;
        while (mid <= hi) {
            if (nums[mid] < pivot) {
                swap(nums, lo++, mid++);
            } else if (nums[mid] == pivot) {
                mid++;
            } else {
                swap(nums, mid, hi--);
            }
        }
        // nums[left..lo-1] < pivot, nums[lo..mid-1] == pivot, nums[mid..right] > pivot

        if (target < lo) {
            return quickSelect(nums, left, lo - 1, target);
        } else if (target < mid) {
            return nums[target]; // target is in the == zone, all same value
        } else {
            return quickSelect(nums, mid, right, target);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 3, 3, 0, 3};
        System.out.println("Before: " + java.util.Arrays.toString(arr));
        threeWaySort(arr);
        System.out.println("After:  " + java.util.Arrays.toString(arr));
        // Expected: [0, 1, 2, 3, 3, 3, 3]

        int[] nums = {3, 2, 1, 5, 6, 4};
        System.out.println("2nd largest: " + findKthLargest(nums, 2)); // 5

        int[] dupes = {3, 3, 3, 3, 3};
        System.out.println("1st largest in all-dupes: " + findKthLargest(dupes, 1)); // 3
    }
}