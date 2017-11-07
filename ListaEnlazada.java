public class ListaEnlazada {
  
  private String valor;
  private ListaEnlazada siguiente;
  
  public ListaEnlazada(String s) {
    this.valor = s;
    this.siguiente = null;
  }
  
  public void concatenate(ListaEnlazada l) {
    ListaEnlazada actual = this;

    while(actual.siguiente != null) {
      actual = actual.siguiente;
    }

    actual.siguiente = l;
  }
  
  public int length(){
    int i;
    
    ListaEnlazada actual = this.siguiente;
    for(i = 1; actual != null; i++) {
      actual = actual.siguiente;
    }
    
    return i;
  }
  
  public ListaEnlazada get(int i) {
    
    if(this.length() < i) {
      return null;
    }
    
    ListaEnlazada ans = this;
    for(int j = 0; j < i; j++) {
      ans = ans.siguiente;
    }
    
    return ans;
    
  }
  
  public int contains(String s) {
    int length = this.length();
    
    for(int i = 0; i < length; i++) {
      ListaEnlazada actual = this.get(i);
      if(actual.valor.equals(s)) {
        return i;
      }
    }
    
    return -1;
  }
  
  
}