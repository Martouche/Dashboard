package dashboard.Widgets;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;
import dashboard.WidgetsState;
import java.util.Objects;

@Entity
@Table(name = "WIDGETS")
public class WidgetsTable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer widgetId;
  private String label;
  @Basic(optional = false)
  @Column(nullable = false)
  private Integer nbrinput;

  public Integer getWidgetId() {
      return widgetId;
  }
  public void setWidgetId(Integer widgetId) {
      this.widgetId = widgetId;
  }

  public String getLabel() {
      return label;
  }
  public void setLabel(String widgetId) {
      this.label = label;
  }
  public int getNbrOfInput() {
    return nbrinput;
  }


  @Override
  public int hashCode() {

      int hash = 7;
      hash = 79 * hash + Objects.hashCode(this.widgetId);
      hash = 79 * hash + Objects.hashCode(this.label);

      return hash;
  }

  @Override
  public boolean equals(Object obj) {
      if (this == obj) {
          return true;
      }
      if (obj == null) {
          return false;
      }
      if (getClass() != obj.getClass()) {
          return false;
      }

      final WidgetsTable other = (WidgetsTable) obj;

      if (this.widgetId != other.widgetId) {
          return false;
      }

      return Objects.equals(this.label, other.label);
  }

  @Override
  public String toString() {

      StringBuilder builder = new StringBuilder();
      builder.append("WIDGETS{widgetId=").append(widgetId).append(", label=")
              .append(label).append("}");

      return builder.toString();
  }
}
