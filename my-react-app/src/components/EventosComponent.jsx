import React, { useEffect, useState } from 'react';

// Simulação de usuário logado (troque pelo endpoint real depois)
const usuarioSimulado = {
  nome: 'Thiago',
  email: 'thiagomesquitaitu@gmail.com',
  cargo: 'GERENTE', // Troque para "FUNCIONARIO" para testar
};

function EventosPage() {
  const [eventos, setEventos] = useState([]);
  const [erro, setErro] = useState('');
  const [novoEvento, setNovoEvento] = useState({
    nome: '',
    data: '',
    titulo: '',
    descricao: '',
    vagas: 0,
    isRemoto: false,
    palestranteId: '', // Simule ou peça o ID do palestrante
    localId: '', // Simule ou peça o ID do local se não for remoto
  });
  const [sucesso, setSucesso] = useState('');

  useEffect(() => {
    fetch('http://localhost:8080/eventos', {
      credentials: 'include',
    })
      .then((res) => {
        if (!res.ok) throw new Error('Erro ao buscar eventos');
        return res.json();
      })
      .then((data) => setEventos(data))
      .catch(() => setErro('Não foi possível carregar os eventos.'));
  }, [sucesso]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setNovoEvento((prev) => ({
      ...prev,
      [name]: type === 'checkbox' ? checked : value,
    }));
  };

  const handleAdicionarEvento = async (e) => {
    e.preventDefault();
    setErro('');
    setSucesso('');
    try {
      const response = await fetch('http://localhost:8080/eventos', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        credentials: 'include',
        body: JSON.stringify({
          ...novoEvento,
          vagas: Number(novoEvento.vagas),
          palestranteId: novoEvento.palestranteId
            ? Number(novoEvento.palestranteId)
            : null,
          localId: novoEvento.isRemoto
            ? null
            : novoEvento.localId
            ? Number(novoEvento.localId)
            : null,
        }),
      });
      if (response.ok) {
        setSucesso('Evento adicionado com sucesso!');
        setNovoEvento({
          nome: '',
          data: '',
          titulo: '',
          descricao: '',
          vagas: 0,
          isRemoto: false,
          palestranteId: '',
          localId: '',
        });
      } else {
        setErro('Erro ao adicionar evento.');
      }
    } catch {
      setErro('Erro ao conectar com o servidor.');
    }
  };

  return (
    <div className="login-container">
      <h2>Lista de Eventos</h2>
      {erro && <p style={{ color: 'red' }}>{erro}</p>}
      {sucesso && <p style={{ color: '#32756F' }}>{sucesso}</p>}
      <ul>
        {eventos.map((evento) => (
          <li key={evento.id}>
            <strong>{evento.nome}</strong> - {evento.data}
          </li>
        ))}
      </ul>
      {usuarioSimulado.cargo === 'GERENTE' && (
        <form
          onSubmit={handleAdicionarEvento}
          style={{
            marginTop: 20,
            display: 'flex',
            flexDirection: 'column',
            gap: 8,
          }}
        >
          <h3>Adicionar Evento</h3>
          <input
            type="text"
            name="nome"
            placeholder="Nome do evento"
            value={novoEvento.nome}
            onChange={handleChange}
            required
          />
          <input
            type="text"
            name="titulo"
            placeholder="Título"
            value={novoEvento.titulo}
            onChange={handleChange}
            required
          />
          <input
            type="date"
            name="data"
            placeholder="Data"
            value={novoEvento.data}
            onChange={handleChange}
            required
          />
          <input
            type="number"
            name="vagas"
            placeholder="Vagas"
            value={novoEvento.vagas}
            onChange={handleChange}
            required
          />
          <textarea
            name="descricao"
            placeholder="Descrição"
            value={novoEvento.descricao}
            onChange={handleChange}
            required
          />
          <label>
            <input
              type="checkbox"
              name="isRemoto"
              checked={novoEvento.isRemoto}
              onChange={handleChange}
            />
            Evento Remoto
          </label>
          {!novoEvento.isRemoto && (
            <input
              type="number"
              name="localId"
              placeholder="ID do Local"
              value={novoEvento.localId}
              onChange={handleChange}
              required
            />
          )}
          <input
            type="number"
            name="palestranteId"
            placeholder="ID do Palestrante"
            value={novoEvento.palestranteId}
            onChange={handleChange}
            required
          />
          <button type="submit">Adicionar Evento</button>
        </form>
      )}
    </div>
  );
}

export default EventosPage;
