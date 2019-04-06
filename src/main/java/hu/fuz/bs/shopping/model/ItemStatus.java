package hu.fuz.bs.shopping.model;

import javax.persistence.Entity;

public enum ItemStatus {
  CREATED("Rögzített"),
  DELETED("Törölt"),
  PURCHASED("Teljesített");

  private final String description;

  ItemStatus(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
