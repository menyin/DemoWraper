public class TestJdk8 {
    public static void main(String[] args) {
        ConnectionHolderFactory cf = SimpleConnectionHolder::new;
        ConnectionHolder ch = cf.create("connection尼玛");
        System.out.println(ch.get());
    }
    @FunctionalInterface
    private interface ConnectionHolderFactory {
        ConnectionHolder create(String connection);
    }
    private interface ConnectionHolder {
        String get();

        void close();
    }
    private static class SimpleConnectionHolder implements ConnectionHolder {
        private final String connection;

        private SimpleConnectionHolder(String connection) {
            this.connection = connection;
        }

        @Override
        public String get() {
            return connection;
        }

        @Override
        public void close() {

        }
    }
}
