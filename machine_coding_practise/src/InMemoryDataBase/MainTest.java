package InMemoryDataBase;

public class MainTest {
    public static void main(String[] args) {
        DataBase db = new InMemoryDatabase(new ConcreteTransactionManager());

        db.put("a", "b");
        db.put("x", "1");

        db.begin();
            db.put("x", "2");
            String val = db.get("x"); // -> should return "2"
            System.out.println(val);
            db.begin();
                db.put("x", "3");
                val = db.get("x"); // -> should return "3"
                System.out.println(val);
            db.commit();
        val = db.get("x"); // -> should return "3"
        System.out.println(val);

        db.rollback();
        val = db.get("x"); // -> should return "1"
        System.out.println(val);

        val = db.get("a");
        System.out.println(val);
    }
}

