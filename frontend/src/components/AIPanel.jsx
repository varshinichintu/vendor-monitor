import { useState } from "react";
import api from "../services/api";

export default function AIPanel() {
  const [input, setInput] = useState("");
  const [loading, setLoading] = useState(false);
  const [response, setResponse] = useState("");

  const handleAsk = async () => {
    setLoading(true);
    const res = await api.post("/ai", { prompt: input });
    setResponse(res.data);
    setLoading(false);
  };

  return (
    <div>
      <input onChange={(e) => setInput(e.target.value)} />

      <button onClick={handleAsk}>Ask AI</button>

      {loading && <p>Loading...</p>}

      {response && (
        <div style={{ border: "1px solid black", padding: "10px" }}>
          {response}
        </div>
      )}
    </div>
  );
}