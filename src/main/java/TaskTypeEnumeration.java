public class TaskTypeEnumeration {
    public enum TaskType {
        T ("todo"), E ("event"), D ("deadline");

        private String type;
        TaskType(String type) {
            this.type = type;
        }

        public String getType(){
            return this.type;
        }
    }
}
