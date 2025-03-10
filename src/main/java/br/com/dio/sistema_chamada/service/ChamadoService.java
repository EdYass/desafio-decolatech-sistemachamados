package br.com.dio.sistema_chamada.service;

import br.com.dio.sistema_chamada.persistence.dao.ChamadoDAO;
import br.com.dio.sistema_chamada.persistence.entity.Chamado;
import br.com.dio.sistema_chamada.persistence.entity.Status;
import lombok.AllArgsConstructor;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ChamadoService {
    private ChamadoDAO chamadoDAO;

    public Chamado abrirChamado(String descricao, Long usuarioId) throws SQLException {
        Chamado chamado = new Chamado();
        chamado.setDescricao(descricao);
        chamado.setDataAbertura(LocalDateTime.now());
        chamado.setStatus(Status.ABERTO);
        chamado.setUsuarioId(usuarioId);
        return chamadoDAO.insert(chamado);
    }


    public void atualizarStatus(Long id, Status status) throws SQLException {
        Optional<Chamado> chamadoOpt = chamadoDAO.findById(id);
        if (chamadoOpt.isPresent()) {
            Chamado chamado = chamadoOpt.get();
            chamado.setStatus(status);
            if (status == Status.CONCLUIDO) {
                chamado.setDataConclusao(LocalDateTime.now());
            }
            chamadoDAO.update(chamado);
        }
    }

    public List<Chamado> listarChamados() throws SQLException {
        return chamadoDAO.findAll();
    }
}
