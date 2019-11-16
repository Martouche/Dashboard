package dashboard.LinkWidgetsUser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;
import dashboard.WidgetsState;
import java.util.Objects;

@Entity
@Table(name = "LINKWIDGETSUSERTABLE")
public class LinkWidgetsUserTable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Basic(optional = false)
  @Column(nullable = false)
  private Integer widgetsid;

  @Basic(optional = false)
  @Column(nullable = false)
  private Integer userid;

  @Basic(optional = false)
  @Column(nullable = false)
  private Integer linkstate;

  private String input;

  @Basic(optional = false)
  @Column(nullable = true)
  Integer SERVICEID;

  public Integer getLinkstate()
  {
      return linkstate;
  }
  public void setLinkstate(Integer linkstate)
  {
      this.linkstate = linkstate;
  }

  public Integer getServiceId()
  {
      return SERVICEID;
  }

  public void setServiceId(Integer SERVICEID)
  {
      this.SERVICEID = SERVICEID;
  }

  public String getInput() {
      return input;
  }
  public void setInput(String input) {
      this.input = input;
  }

  public Integer getId() {
      return id;
  }
  public void setId(Integer id) {
      this.id = id;
  }

  public Integer getWidgetId() {
      return widgetsid;
  }
  public void setWidgetId(Integer widgetsid) {
      this.widgetsid = widgetsid;
  }

  public Integer getUserId() {
      return userid;
  }
  public void setUserId(Integer userid) {
      this.userid = userid;
  }

  @Override
  public int hashCode() {

      int hash = 7;
      hash = 79 * hash + Objects.hashCode(this.id);
      hash = 79 * hash + Objects.hashCode(this.userid);
      hash = 79 * hash + Objects.hashCode(this.widgetsid);

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

      final LinkWidgetsUserTable other = (LinkWidgetsUserTable) obj;
      if (this.userid != other.userid) {
          return false;
      }

      if (this.widgetsid != other.widgetsid) {
          return false;
      }

      return Objects.equals(this.id, other.id);
  }

  @Override
  public String toString() {

      StringBuilder builder = new StringBuilder();
      builder.append("LINKWIDGETSUSERTABLE{id=").append(id).append(", widgetsid=")
              .append(widgetsid).append(", userid=")
              .append(userid).append("}");

      return builder.toString();
  }
}
