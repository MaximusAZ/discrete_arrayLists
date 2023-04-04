
    //testing the remove-by-value method
    private static int testRemoveByValue() {
	int score = 0;
	System.out.println("\nTesting remove-by-value method...");
	int count = 1;
	boolean e = false;
	boolean a = false;
	while(count <= 50) {
	    int num = gen.nextInt(20);
	    try {
		e = exp.remove(new Integer(num));
		a = act.removeByValue(num);
	    } catch (Exception ex) {
		ex.printStackTrace();
		return 0;
	    }
	    
	    if(e != a) {
		System.out.println("Removed items do not match.");
		System.out.println("Expected: " + e);
		System.out.println("Actual: " + a);
		return 0;
	    }
	    count++;
	}
	if(checkLists()) {
	    score += 3;
	}
	if(checkCounts("removeByValue")) {
	    score += 2;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }

    //testing the set method
    private static int testSet() {
	int score = 0;
	System.out.println("\nTesting set method...");
	int count = 1;
	int e = -1;
	int a = -1;
	while(count <= 10) {
	    int i = gen.nextInt(exp.size());
	    int num = gen.nextInt(20);
	    e = exp.set(i, num);
	    a = act.set(i, num);
	    count++;
	}
	if(checkLists()) {
	    score += 3;
	}
	if(checkCounts("set")) {
	    score += 2;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }


    //runs various methods many times and tests the results
    private static int finalTest() {
	act = new ArrList(10);
	exp = new ArrayList<Integer>();
	int score = 0;
	System.out.println("\nTesting for robustness...");
	int count = 0;
	//add a bunch of elements to the lists
	System.out.println("Adding elements...");
	while(count < 500) {
	    int num = gen.nextInt(20);//System.out.println("adding " + num);
	    exp.add(num);
	    act.addLast(num);
	    count++;
	}
	if(checkLists())
	    score += 1;
	while(count > 0) {
	    int num = gen.nextInt(20);//System.out.println("adding " + num);
	    exp.add(0, num);
	    act.addFirst(num);
	    count--;
	}
	if(checkLists())
	    score += 1;
	while(!exp.isEmpty()) {
	    try {
		exp.remove(0);
		act.removeFirst();
		if(!exp.isEmpty()) {
		    exp.remove(exp.size()-1);
		    act.removeLast();
		}
	    } catch (Exception ex) {
		ex.printStackTrace();
		return score;
	    }
	}
	if(act.isEmpty())
	    score += 1;
	if(checkCounts("final")) {
	    score += 2;
	}
	System.out.println("Expected Score: " + score);
	return score;
    }	    