package Presentation.GUI;

import Presentation.Listeners.EventItemSelected;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class AdminGUI extends GUI
{
    JButton logoutButton = GUI.createButton("Log out");
    private PanelCazare panelCazare = new PanelCazare(logoutButton);
    ArrayList<RoomData> lista;
    private JComponent panel2 = makeTextPanel("Panel #2");
    private JComponent panel3 = makeTextPanel("Panel #3");
    private JComponent panel4 = makeTextPanel("Panel #4");

    private JTabbedPane tabbedPane;

    public AdminGUI()
    {
        this.setSize(1500, 700);
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(new GridLayout(1, 1));
        this.getContentPane().add(makeTabbedPane());

        ArrayList<RoomData> lista = new ArrayList<>();
        ArrayList<String> students = new ArrayList<>();
        students.add("Popescu");
        students.add("Ionescu"); students.add("Popescu");
        students.add("Ionescu");

        for(int i = 0; i < 30; i++)
        {
            if(i%2==0)
            {
                lista.add(new RoomData(0, i, 4, students, "M", i+1, null));
            }
            else
            {
                lista.add(new RoomData(0, i, 4, students, "F", 0, null));
            }
        }

        panelCazare.getRoomView().setItems(lista, 1);

        ArrayList<String> head = new ArrayList<>();
        head.add("Nume");
        ArrayList<String> students2 = new ArrayList<>();
        students2.add("Popescu");
        students2.add("Ionescu");
        //try
        //{
        //    setTable(head,students2,panelCazare.getStudentTable());
        //} catch (IllegalAccessException e)
        //{
        //    e.printStackTrace();
        //} catch (NoSuchFieldException e)
        //{
        //    e.printStackTrace();
        //}
        //panelCazare.setStudentTable();
    }

    private JTabbedPane makeTabbedPane()
    {
        Insets insets = UIManager.getInsets("TabbedPane.contentBorderInsets");
        insets.top = 0;
        insets.bottom = 0;
        insets.left = 0;
        insets.right = 0;
        UIManager.put("TabbedPane.contentBorderInsets", insets);
        UIManager.put("TabbedPane.selected", new Color(39, 39, 39));

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFocusable(false);
        tabbedPane.setBackground(new Color(143, 0, 0));
        tabbedPane.setForeground(Color.WHITE);

        tabbedPane.addTab("Cazare", null, panelCazare, "Assign students to rooms");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.getSelectedComponent();

        tabbedPane.addTab("Cereri", null, panel2, "Requests from students");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("Anunturi", null, panel3, "Announcements");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        tabbedPane.addTab("Studenti", null, panel4, "All students");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        return tabbedPane;
    }

    private JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        panel.setBackground(new Color(39, 39, 39));
        return panel;
    }

    public void setCazareRefreshListener(ActionListener actionListener)
    {
        panelCazare.addRefreshButtonListener(actionListener);
    }

    public JTable getUsersTable()
    {
        return panelCazare.getStudentTable();
    }

    public void refresh(ArrayList<ArrayList<String>> users, ArrayList<ArrayList<String>> camere, int selectedFloor)
    {
        ArrayList<ArrayList<String>> displayData = new ArrayList<>();
        lista = new ArrayList<>();
        int etaje = 0;

        for(ArrayList<String> row : camere)
        {
            lista.add(new RoomData(Integer.parseInt(row.get(0)),
                                   Integer.parseInt(row.get(1)),
                                   Integer.parseInt(row.get(2)),
                                   new ArrayList<String>(),
                                   null,
                                   0,
                                   new ArrayList<String>()));
            if(Integer.parseInt(row.get(1)) / 100 > etaje);
            {
                etaje = Integer.parseInt(row.get(1)) / 100;
            }
        }

        if( panelCazare.getEtaje() != etaje)
            panelCazare.setFloors(etaje);

        boolean added;
        for(ArrayList<String> row : users)
        {
            ArrayList<String> displayRow = new ArrayList<>();
            added = false;

            for(RoomData rd : lista)
            {
                if(row.get(7) != null)
                    if(rd.id == Integer.parseInt(row.get(7)))
                    {
                        rd.students.add(row.get(1) + " " + row.get(2));
                        rd.studentsCNP.add(row.get(0));
                        rd.tipCamera = row.get(6);
                        added = true;
                    }
            }

            if(added == false)
            {
                displayRow.add(row.get(0)); //cnp
                displayRow.add(row.get(1) + " " + row.get(2)); //nume + prenume
                displayRow.add(row.get(6)); //sex
                displayData.add(displayRow);
            }
        }

        panelCazare.getRoomView().setItems(lista, selectedFloor + 1);
        panelCazare.setTable(panelCazare.getStudentTable(), displayData);
    }

    public void addTableListerner(MouseListener listener)
    {
        panelCazare.addTableListener(listener);
    }

    public void addLogoutListener(ActionListener listener)
    {
        logoutButton.addActionListener(listener);
    }

    public void setRefreshListener(ActionListener listeners)
    {
        panelCazare.addRefreshButtonListener(listeners);
    }

    public void addFloorListListener(ListSelectionListener lsl)
    {
        panelCazare.addFloorListListener(lsl);
    }

    public JList<String> getList()
    {
        return panelCazare.getFloorList();
    }

    public void setRoomListener(EventItemSelected eis)
    {
        panelCazare.getRoomView().setEvent(eis);
    }
}

class PanelCazare extends JPanel
{
    private RoomView roomView;
    private JTable studentTable;
    private JPanel floorPanel;
    private JButton refreshButton;
    private JButton logOutButton;
    JList<String> floorList;
    String[] header = { "CNP", "Student", "Sex"};
    private int nr_etaje;

    PanelCazare(JButton logOutButton)
    {
        this.roomView = new RoomView(1000,700);
        this.setBackground(new Color(39, 39, 39));
        JScrollPane panelRooms = new JScrollPane();
        panelRooms.getVerticalScrollBar().setBackground(new Color(143, 0, 0));
        panelRooms.getHorizontalScrollBar().setBackground(new Color(143, 0, 0));
        panelRooms.setViewportView(roomView);
        panelRooms.setBackground(new Color(39, 39, 39));
        panelRooms.getViewport().setOpaque(false);
        panelRooms.setViewportBorder(null);
        panelRooms.setBorder(null);

        this.studentTable = new JTable();
        JScrollPane panelStudenti = new JScrollPane(studentTable);
        panelStudenti.getViewport().setBackground(new Color(39, 39, 39));
        panelStudenti.getVerticalScrollBar().setBackground(new Color(143, 0, 0));
        panelStudenti.getHorizontalScrollBar().setBackground(new Color(143, 0, 0));
        studentTable.setBackground(new Color(39, 39, 39));
        studentTable.setForeground(Color.WHITE);
        studentTable.setGridColor(new Color(143, 0, 0));
        studentTable.getTableHeader().setBackground(new Color(143, 0, 0));
        studentTable.getTableHeader().setForeground(Color.WHITE);
        studentTable.setAutoCreateRowSorter(true);

        floorPanel = new JPanel();
        floorPanel.setBackground(new Color(39, 39, 39));
        floorPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        floorList = new JList<>();
        floorList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        floorList.setVisibleRowCount(1);
        floorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        floorList.setForeground(Color.WHITE);
        floorList.setBackground(new Color(39, 39, 39));
        floorPanel.add(floorList);

        //todo
        setFloors(0);

        JPanel panelButtons = new JPanel();
        panelButtons.setBackground(new Color(39, 39, 39));
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        refreshButton = GUI.createButton("Refresh");
        refreshButton.setPreferredSize(new Dimension(225, 25));
        this.logOutButton = logOutButton;
        logOutButton.setPreferredSize(new Dimension(225, 25));
        panelButtons.add(refreshButton);
        panelButtons.add(logOutButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(panelRooms, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                                .addComponent(floorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE))

                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(panelStudenti, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                               .addComponent(panelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(panelRooms, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                                .addComponent(panelStudenti, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(floorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                .addComponent(panelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
        );
    }

    public void setFloors(int n)
    {
        //floorPanel.removeAll();
        this.nr_etaje = n;
        DefaultListModel<String> l1 = new DefaultListModel<>();
        for(int i = 1; i <= nr_etaje; i++)
        {
            l1.addElement(" Etajul " + i + " ");
        }
        floorList.setModel(l1);
        repaint();
        revalidate();
    }

    public JList<String> getFloorList()
    {
        return floorList;
    }

    JTable getStudentTable()
    {
        return studentTable;
    }

    public void addTableListener(MouseListener listener)
    {
        studentTable.addMouseListener(listener);
    }

    public void addFloorListListener(ListSelectionListener lsl)
    {
        floorList.addListSelectionListener(lsl);
    }

    public void setTable(JTable table, ArrayList< ArrayList<String> > list)
    {
        DefaultTableModel dtm = new DefaultTableModel()
        {
            @Override
            public String getColumnName(int index)
            {
                return header[index];
            }

            @Override
            public boolean isCellEditable(int row, int col)
            {
                return false;
            }

            @Override
            public int getColumnCount()
            {
                return header.length;
            }

            @Override
            public int getRowCount()
            {
                return list.size();
            }
        };

        int i = 0, j;
        for(ArrayList<String> row : list)
        {
            j = 0;
            for(String col : row)
            {
                dtm.setValueAt(col, i, j);
                j++;
            }
            i++;
        }
        table.setModel(dtm);
        repaint();
    }

    RoomView getRoomView()
    {
        return roomView;
    }

    void setRoomView(RoomView roomView)
    {
        this.roomView = roomView;
    }

    void addRefreshButtonListener(ActionListener listener)
    {
        refreshButton.addActionListener(listener);
    }

    public int getSelectedFloor()
    {
        return floorList.getSelectedIndex()+1;
    }

    public void setSelectedFloor(int selectedFloor)
    {
        floorList.setSelectedIndex(selectedFloor);
    }

    public int getEtaje()
    {
        return nr_etaje;
    }
}

class RoomData
{
    int id;
    int nrCamera;
    int nrLocuri;
    ArrayList<String> students;
    ArrayList<String> studentsCNP;
    String tipCamera;
    int cameraComuna;
    int etaj;

    RoomData(int id, int nrCamera, int nrLocuri, ArrayList<String> students, String tipCamera, int cameraComuna, ArrayList<String> studentsCNP)
    {
        this.id = id;
        this.nrCamera = nrCamera;
        this.nrLocuri = nrLocuri;
        this.students = students;
        this.tipCamera = tipCamera;
        this.cameraComuna = cameraComuna;
        this.etaj = nrCamera / 100;
        this.studentsCNP = studentsCNP;
    }
}

class RoomView extends JPanel
{
    private ArrayList<RoomData> items = new ArrayList<RoomData>();
    private Color effectColor;
    private Point point;
    private float[] dist = {0.0f, 0.5f, 1.0f};
    private Color[] colors;
    private int itemWidth = 150;
    private int itemHeight = 150;
    private int space = 4;
    private int lineSize = 4;
    private int showLine = 1;
    private EventItemSelected event;
    private int etaj;

    RoomView(int width, int height)
    {
        setSize(width, height);
        setLayout(new WrapLayout(WrapLayout.LEFT, space + lineSize * 2, space + lineSize * 2));
        setOpaque(false);
        setEffectColor(new Color(255, 255, 255));
        setForeground(new Color(205, 205, 205));

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                point = me.getPoint();
                checKMouse();
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent me) {
                point = me.getPoint();
                checKMouse();
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent me) {
                point = null;
                checKMouse();
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                if (event != null && point != null) {
                    int index = getMouseOver();
                    if(me.getButton() == MouseEvent.BUTTON1)
                    {
                        if (index >= 0)
                        {
                            event.selected(items.get(index).id);
                        }
                    }
                    if(me.getButton() == MouseEvent.BUTTON3)
                    {
                        event.deleting(items.get(index).id, items.get(index).students, items.get(index).studentsCNP);
                    }

                }
            }
        });
    }

    private void initItem()
    {
        removeAll();
        ArrayList<RoomData> aux = new ArrayList<>();
        for(RoomData d : items)
        {
            if(d.etaj == etaj)
                aux.add(d);
        }
        items = aux;

        for (RoomData d : items)
        {
            add(createRoom(d));
        }
        repaint();
        revalidate();
    }

    private JComponent createRoom(Object data)
    {
        JPanel room = new JPanel();
        room.setPreferredSize(new Dimension(itemWidth - lineSize * 2, itemWidth - lineSize * 2));
        RoomData d = (RoomData) data;

        if(d.students.size() != 0)
        {//set color based on sex
            if(Objects.equals(d.tipCamera, "F"))
                room.setBackground(new Color(171, 21, 236));
            else if(Objects.equals(d.tipCamera, "M"))
                room.setBackground(new Color(61, 61, 238));
            //else room.setBackground(new Color(100, 100, 100));
        }
        else
        {//empty room
            room.setBackground(new Color(39, 39, 39));

        }

        JLabel camera = new JLabel("Camera " + d.nrCamera);
        camera.setForeground(getForeground());

        JLabel number = new JLabel(d.students.size() + "/" + d.nrLocuri);
        number.setForeground(getForeground());

        JLabel cameraComuna = new JLabel("(" + d.cameraComuna + ")");
        if(d.cameraComuna != 0) cameraComuna.setForeground(getForeground());
        else cameraComuna.setForeground(room.getBackground());

        String students = "<html>";
        for (String s : d.students)
        {
            students += s + "<br>";
        }
        students += "</html>";
        JLabel labelStudenti = new JLabel(students);
        labelStudenti.setForeground(getForeground());

        room.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 0;

        c.anchor = GridBagConstraints.FIRST_LINE_START;
        room.add(cameraComuna,c);

        c.anchor = GridBagConstraints.PAGE_START;
        room.add(camera, c);

        c.anchor = GridBagConstraints.FIRST_LINE_END;
        room.add(number,c);

        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        room.add(labelStudenti, c);

        return room;
    }

    private JLabel createLabel(Object data) {
        JLabel label = new JLabel("<html>" + data.toString().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
        label.setFont(label.getFont().deriveFont(Font.PLAIN, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(getForeground());
        label.setPreferredSize(new Dimension(itemWidth, itemHeight));
        return label;
    }

    public void setEffectColor(Color effectColor) {
        this.effectColor = effectColor;
        int red = effectColor.getRed();
        int green = effectColor.getGreen();
        int blue = effectColor.getBlue();
        colors = new Color[]{new Color(red, green, blue, 70), new Color(red, green, blue, 20), new Color(red, green, blue, showLine)};
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        Shape mouseOver = null;
        int width = getWidth();
        int height = getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = space * 2;
        int y = space * 2;
        int size = 150;
        for (int i = 0; i < items.size(); i++)
        {
            if(items.get(i).etaj != etaj) continue;
            Shape s = createShape(x, y, itemWidth, itemHeight);
            if (mouseOver == null && isMouseOver(x, y, itemWidth, itemHeight)) {
                mouseOver = s;
            }
            g2d.fill(s);
            x += space + itemWidth;
            if (x + space + itemWidth > width) {
                x = space * 2;
                y += space + itemHeight;
            }
        }
        g2d.setComposite(AlphaComposite.SrcIn);
        if (point != null)
        {
            RadialGradientPaint p = new RadialGradientPaint(point, size, dist, colors);
            g2d.setPaint(p);
        }
        else
        {
            g2d.setColor(new Color(255, 255, 255, showLine));
        }
        g2d.fillRect(0, 0, width, height);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        if (mouseOver != null) {
            g2d.setColor(effectColor);
            g2d.fill(mouseOver);
        }
        g2d.dispose();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        super.paint(g);
    }

    private Shape createShape(int x, int y, int widht, int height) {
        Area area = new Area(new Rectangle2D.Double(x, y, widht, height));
        area.subtract(new Area(new Rectangle2D.Double(x + lineSize, y + lineSize, widht - lineSize * 2, height - lineSize * 2)));
        return area;
    }

    private boolean isMouseOver(int x, int y, int width, int height) {
        boolean over = false;
        if (point != null) {
            if (new Rectangle2D.Double(x, y, width, height).contains(point)) {
                over = true;
            }
        }
        return over;
    }

    private int getMouseOver() {
        int width = getWidth();
        int x = space * 2;
        int y = space * 2;
        for (int i = 0; i < items.size(); i++)
        {
            if (isMouseOver(x, y, itemWidth, itemHeight)) {
                return i;
            }
            x += space + itemWidth;
            if (x + space + itemWidth > width) {
                x = space * 2;
                y += space + itemHeight;
            }
        }
        return -1;
    }

    private void checKMouse() {
        if (point == null) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } else {
            int index = getMouseOver();
            if (index == -1) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } else {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        }
    }

    public ArrayList getItems()
    {
        return items;
    }

    public void setItems(ArrayList items, int etaj) {
        this.items = items;
        this.etaj = etaj;
        initItem();
    }

    public EventItemSelected getEvent() {
        return event;
    }

    public void setEvent(EventItemSelected event) {
        this.event = event;
    }
}
