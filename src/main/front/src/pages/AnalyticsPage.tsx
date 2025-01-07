import Header from "@/components/Header";

export default function AnalyticsPage() {
  return (
    <div className="px-32 my-8">
      <Header />
      <div className="flex justify-center">
        <table className="border-collapse overflow-hidden rounded-xl">
          <thead className="bg-[#E4C1FF] text-[#8300E7]">
            <tr>
              <th className="p-4 text-start">Стажёр</th>
              <th className="p-4 text-start">Статус стажировки</th>
              <th className="p-4 text-start">Оценка</th>
              <th className="p-4 text-start">Рекомендован</th>
            </tr>
          </thead>
          <tbody className="bg-[#F3DFFF]">
            <tr>
              <td className="p-4">
                <div className="flex gap-2 items-center">
                  <Ava />
                  <p>Макаров Алексей Алексеевич</p>
                </div>
              </td>
              <td className="p-4">Проходит</td>
              <td className="p-4">0</td>
              <td className="p-4">Да</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
}

export function Ava() {
  return (
    <div>
      <div
        className="rounded-xl w-12 h-12 cursor-pointer bg-white"
        style={{
          backgroundImage: `url()`,
          backgroundSize: "cover",
          backgroundPosition: "center",
        }}
        tabIndex={0}
        role="button"
      />
    </div>
  );
}
