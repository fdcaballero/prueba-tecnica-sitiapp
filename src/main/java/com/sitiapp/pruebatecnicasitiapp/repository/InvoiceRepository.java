package com.sitiapp.pruebatecnicasitiapp.repository;

import com.sitiapp.pruebatecnicasitiapp.dto.ReportProduct;
import com.sitiapp.pruebatecnicasitiapp.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query(value = "select sb.fecha as fecha , sb.nombre as nombre, max(sb.subtotal) as total " +
            "from ( select f.fecha as fecha, p.nombre, sum(factura_detalle.valor_unitario) as subtotal\n" +
            "      from factura_detalle\n" +
            "               join facturas f on f.id = factura_detalle.factura_id\n" +
            "               inner join productos p on factura_detalle.product_id = p.id\n" +
            "      group by fecha, p.nombre) as sb\n" +
            "group by fecha", nativeQuery = true)
    List<?> FindproductMostSelling();


}
