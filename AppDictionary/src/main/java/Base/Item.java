package Base;

import java.util.ArrayList;
import java.util.Collections;

public class Item {
    public static int binarySearch(ArrayList<Word> arr, String key) {
        int low = 0;
        int high = arr.size() - 1;
        while (high >= low) {
            int mid = (low + high) / 2;
            if (arr.get(mid).getWord().equals(key)) {
                return mid;
            } else {
                if (arr.get(mid).getWord().compareTo(key) > 0) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    private static int partition(ArrayList<Word> arr, int low, int high) {
        Word pivot = arr.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr.get(j).getWord().compareTo(pivot.getWord()) < 0) {
                i++;
                Collections.swap(arr, i, j);
            }
        }

        Collections.swap(arr, i + 1, high);
        return i + 1;
    }

    public static void sort(ArrayList<Word> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }
}
