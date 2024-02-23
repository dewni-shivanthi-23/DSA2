public class Queue {
    private int front;//Index of the front element in the queue
    private int rear;// Index of the last element in the queue
    private int capacity;//Maximum number of elements the queue can hold.
    private Car[] elements;//: Array of Car objects to store queue elements.

    public Queue(int size){
        front=rear=-1;
        capacity=size; //Sets front and rear to -1 indicating an empty queue.
        elements=new Car[capacity]; //Creates the elements array with the specified capacity.
    }

    //Checks if both front and rear are at the ends of the array, indicating no space for new elements.
    boolean isFull(){
        if(front==0 && rear==capacity-1){
            return true;
        }
        return false;
    }

    //Checks if front is -1, signifying an empty queue.
    boolean isEmpty(){
        if(front==-1)
            return true;
        else
            return false;
    }

    public void enqueue(Car car) {
        if(isFull()){
            System.out.println("Overflow");
        }
        else{
            if(front==-1){
                front=0;//front is -1, set it to 0 for the first element.
            }
            rear++;//front is -1, set it to 0 for the first element.
            elements[rear]=car;//Add the car object to the elements array at the rear index.
        }
    }

    public Car dequeue() {
        if(isEmpty()){
            System.out.println("There are no cars in the garage");
            return null;
        }
        else{
            Car car=elements[front];//Store the front element (elements[front]) in a temporary variable (car).
            if(front>=rear){
                front=-1;rear=-1;
            }
            else{
                front++;
            }
            return car;
        }
    }

    public Car peek() {
        if (isEmpty()){
            //if the queue is empty,print the message and return null
            System.out.println("There are no cars in the garage");
            return null;
        }
        Car car=elements[front];
        //return the car at the front without deqeuing
        return car;
    }


    public int size() {
        // Handle empty queue case:
        if (isEmpty()) {
            return 0;
        }

        // Calculate size based on front and rear indices:
        // Note: If front is 0, the size is simply rear + 1.
        // If front is not 0, it means the queue has wrapped around, and we need to
        // add the capacity to account for the wraparound.
        return rear - front + 1 + (front == 0 ? 0 : capacity);
    }


}
