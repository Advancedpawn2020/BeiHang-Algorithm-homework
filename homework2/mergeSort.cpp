
void mergeSort(std::string arr[], int start, int end);

void merge(std::string arr[], int start, int mid, int end);

void mergeSort(std::string arr[], int start, int end) {
    if (start >= end) {
        return;
    } else {
        int mid = (start + end) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);
        merge(arr, start, mid + 1, end);
    }
}

void merge(std::string arr[], int start, int mid, int end) {
    int LEFT_SIZE = mid - start;
    int RIGHT_SIZE = end - mid + 1;
    std::string left[LEFT_SIZE];
    std::string right[RIGHT_SIZE];

    for (int i = start; i < mid; i++) {
        left[i - start] = arr[i];
    }

    for (int i = mid; i <= end; i++) {
        right[i - mid] = arr[i];
    }

    int i = 0, j = 0, k = start;
    while (i < LEFT_SIZE && j < RIGHT_SIZE) {
        if (left[i] < right[j]) {
            arr[k++] = left[i++];
        } else {
            arr[k++] = right[j++];
        }
    }
    while (i < LEFT_SIZE) {
        arr[k++] = left[i++];
    }

    while (j < RIGHT_SIZE) {
        arr[k++] = right[j++];
    }
}
