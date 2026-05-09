import { useEffect, useState } from "react";
import api from "../services/api";

export default function VendorList() {

  const [data, setData] = useState([]);
  const [page, setPage] = useState(0);

  const [search, setSearch] = useState("");
  const [status, setStatus] = useState("");

  useEffect(() => {

    const delay = setTimeout(() => {

      let url = "/vendors";

      // search + filter
      if (search || status) {
        url = `/vendors/search?q=${search}&status=${status}`;
      }

      api.get(url)
        .then((res) => {

          // pagination manually
          const start = page * 5;
          const end = start + 5;

          setData(res.data.slice(start, end));

        })
        .catch((err) => {
          console.log(err);
          setData([]);
        });

    }, 500);

    return () => clearTimeout(delay);

  }, [search, status, page]);

  return (
    <div style={{ padding: "20px", color: "white" }}>

      <h2 style={{ textAlign: "center" }}>
        Vendor List
      </h2>

      {/* FILTER BAR */}
      <div
        style={{
          marginBottom: "20px",
          display: "flex",
          gap: "10px",
          justifyContent: "center"
        }}
      >

        <input
          type="text"
          placeholder="Search"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          style={{
            padding: "8px",
            width: "250px"
          }}
        />

        <select
          value={status}
          onChange={(e) => setStatus(e.target.value)}
          style={{
            padding: "8px"
          }}
        >
          <option value="">All</option>
          <option value="ACTIVE">Active</option>
          <option value="INACTIVE">Inactive</option>
        </select>

      </div>

      {/* TABLE */}
      <table
        border="1"
        width="100%"
        cellPadding="10"
        style={{
          borderCollapse: "collapse",
          textAlign: "center"
        }}
      >

        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Status</th>
            <th>Rating</th>
          </tr>
        </thead>

        <tbody>

          {data.length === 0 ? (

            <tr>
              <td colSpan="4">
                No Data Found
              </td>
            </tr>

          ) : (

            data.map((v) => (

              <tr key={v.id}>
                <td>{v.name}</td>
                <td>{v.email}</td>
                <td>{v.status}</td>
                <td>{v.rating}</td>
              </tr>

            ))

          )}

        </tbody>

      </table>

      {/* PAGINATION */}
      <div
        style={{
          marginTop: "20px",
          display: "flex",
          justifyContent: "center",
          gap: "10px"
        }}
      >

        <button
          onClick={() => setPage(page - 1)}
          disabled={page === 0}
        >
          Prev
        </button>

        <button
          onClick={() => setPage(page + 1)}
        >
          Next
        </button>

      </div>

    </div>
  );
}