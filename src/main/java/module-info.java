module com.m06uf1practa.m06uf1practa {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.m06uf1practa.m06uf1practa to javafx.fxml;
    exports com.m06uf1practa.m06uf1practa;
    requires nu.xom;
    opens com.m06uf1practa.m06uf1practa.Presentacion to javafx.fxml;
    opens com.m06uf1practa.m06uf1practa.Datos to javafx.fxml;
    opens com.m06uf1practa.m06uf1practa.Logica to javafx.fxml;
    opens com.m06uf1practa.m06uf1practa.Modelos to javafx.fxml;
    exports com.m06uf1practa.m06uf1practa.Presentacion;
    exports com.m06uf1practa.m06uf1practa.Logica;
    exports com.m06uf1practa.m06uf1practa.Datos;
    exports com.m06uf1practa.m06uf1practa.Modelos;
}
