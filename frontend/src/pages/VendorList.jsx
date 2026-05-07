import { useEffect, useState } from "react";
import api from "../services/api";

export default function VendorList() {
  const [data, setData] = useState([]);
  const [page, setPage] = useState(0);

  useEffect(() => {
    api.get(`/vendors?page=${page}&size=5`)
      .then((res) => setData(res.data));
  }, [page]);

  return (
    <div>
      <h2>Vendor List</h2>

      <table border="1">
        <thead>
          <tr>
            <th>Name</th>
          </tr>
        </thead>
        <tbody>
          {data.map((v) => (
            <tr key={v.id}>
              <td>{v.name}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <button onClick={() => setPage(page - 1)}>Prev</button>
      <button onClick={() => setPage(page + 1)}>Next</button>
    </div>
  );
}