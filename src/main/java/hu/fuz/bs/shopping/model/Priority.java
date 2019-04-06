package hu.fuz.bs.shopping.model;

public enum Priority {
  HIGH("Mott"),
  NORMAL("Közepes"),
  LOW("Majd");

  private String description;
  Priority(String str) {
    description = str;
  }

  public String getDescription(){
    return description;
  }
}
