import { useEffect, useState } from "react";
import api from "../services/api";

export default function VendorList() {
  const [data, setData] = useState([]);
  const [page, setPage] = useState(0);

  const [search, setSearch] = useState("");
  const [status, setStatus] = useState("");

  // debounce search
  useEffect(() => {
    const delay = setTimeout(() => {
      api.get(
        `/vendors/search?q=${search}&status=${status}&page=${page}&size=5`
      ).then((res) => setData(res.data));
    }, 500);

    return () => clearTimeout(delay);
  }, [search, status, page]);

  return (
    <div>
      <h2>Vendor List</h2>

      {/* FILTER BAR */}
      <div style={{ marginBottom: "10px" }}>
        <input
          placeholder="Search"
          onChange={(e) => setSearch(e.target.value)}
        />

        <select onChange={(e) => setStatus(e.target.value)}>
          <option value="">All</option>
          <option value="ACTIVE">Active</option>
          <option value="INACTIVE">Inactive</option>
        </select>
      </div>

      {/* TABLE */}
      <table border="1">
        <thead>
          <tr>
            <th>Name</th>
          </tr>
        </thead>

        <tbody>
          {data.length === 0 ? (
            <tr>
              <td>No Data Found</td>
            </tr>
          ) : (
            data.map((v) => (
              <tr key={v.id}>
                <td>{v.name}</td>
              </tr>
            ))
          )}
        </tbody>
      </table>

      {/* PAGINATION */}
      <div>
        <button onClick={() => setPage(page - 1)} disabled={page === 0}>
          Prev
        </button>
        <button onClick={() => setPage(page + 1)}>Next</button>
      </div>
    </div>
  );
}